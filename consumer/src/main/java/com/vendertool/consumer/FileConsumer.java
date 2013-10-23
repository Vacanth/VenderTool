package com.vendertool.consumer;

import java.io.IOException;
import java.util.List;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;
import com.vendertool.sharedapp.RestServiceClientHelper;
import com.vendertool.sharedapp.URLConstants;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;
import com.vendertool.sharedtypes.rnr.fps.ProcessTaskRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class FileConsumer {
	private static final Logger logger = Logger.getLogger(FileConsumer.class);
	private Connection connection = null;
	private Channel channel = null;
	
	private QueueingConsumer consumer;
	
	private String routingKey = null; 
	private String exchangeName = null;
	private String queueName = null;
	private List<String> exchanges;			// to create multiple exchanges once other consumers are needed	
	private boolean durableQueue = false;
	private boolean durableExchange = false;
	private int totalMsgsToPrefetch = 10;
	private boolean autoAck = false;
	private String consumerTag = null;
	private int channelNumber = 1234;

	 public FileConsumer init() throws Exception {		 
		logger.log(Level.INFO, "FileConsumer:: " + FileConsumer.class.getName() + " initstarted.");
		
		new ClassPathXmlApplicationContext("consumer-application-context.xml");
		
		 /*ConnectionParameters params = new ConnectionParameters();
		 params.setUsername(ConsumerConstants.Rabbitmq.RABBIT_AMQP_HOST_USERNAME);
		 params.setPassword(ConsumerConstants.Rabbitmq.RABBIT_AMQP_HOST_PASSWORD);
		 params.setVirtualHost(ConsumerConstants.Rabbitmq.RABBIT_AMQP_VIRTUAL_HOST);
		 params.setRequestedHeartbeat(ConsumerConstants.Rabbitmq.RABBIT_AMQP_HEARTBEAT);*/
		 
		 ConnectionFactory factory = new ConnectionFactory();
		 factory.setHost(ConsumerConstants.Rabbitmq.RABBIT_AMQP_HOST);

		 connection = factory.newConnection();		 
		 try {
			 // Create a channel
			 channel = connection.createChannel(channelNumber);
			 // if channel created successfully
			 if(channel!=null){
				 // TODO: will declare only DIRECT exchange for now. Once Task consumer comes in, needs to create multiple exchanges
				// this.channel.exchangeDeclare(exchangeName, ExchangeTypeEnum.DIRECT.getExchangeType(), durableExchange);
				 // Create a Queue. Will create just one queue for fileConsumer
				 channel.queueDeclare(queueName, durableQueue, false, false, null);

				 // Bind the queue to the exchange using the routing key as this is a DIRECT exchange
				 channel.queueBind(queueName, exchangeName, ConsumerConstants.Rabbitmq.RABBIT_AMQP_ROUTING_KEY);

				 // prefetch message in one chunk for processing
				 channel.basicQos(totalMsgsToPrefetch);

				 // create a queueing consumer
				 consumer = new QueueingConsumer(channel);

				 // Create a consumer and manually ack message after processing
				 String crConsumerTag = channel.basicConsume(queueName, autoAck,consumerTag, consumer);
				 // cancel consumer if consumer cannot be created
				 if(!crConsumerTag.equalsIgnoreCase(consumerTag)) {
					 channel.basicCancel(crConsumerTag);
				 }
			 }
		 } catch(IOException ex) {
			 if(channel!=null) {
				 // unbind the queue, delete exchange and queue
				 try {
					 channel.queueUnbind(queueName, exchangeName, routingKey);
					 channel.queueDelete(queueName);
					 channel.exchangeDelete(exchangeName);
					 channel.close();
				 }
				 catch(IOException e){}
			 }
		 }
	    return this;
	  }
	 
	 public void setChannelNumber(int channelNumber) {
		 this.channelNumber = channelNumber;
	 }
	 public void setConsumerTag(String consumerTag) {
		 this.consumerTag = consumerTag;
	 }
	 
	 public void setAutoAck(boolean autoAck) {
		 this.autoAck = autoAck;
	 }
	 
	 public void setDurableQueue(boolean durable) {
		 this.durableQueue = durable;
	 }
	 
	 public void setDurableExchange(boolean durable) {
		 this.durableExchange = durable;
	 }
	 
	 public void setPrefetchMsgCount(int totalMsgsToPrefetch) {
		 this.totalMsgsToPrefetch = totalMsgsToPrefetch;
	 }
	 
	 public boolean isChannelActive() {
		return channel != null && channel.isOpen() ; 
	 }
	 
	 public void setExchangeName(String exchangeName){
		this.exchangeName = exchangeName;
	 }
	 
	 public void setQueueName(String queueName) {
		 this.queueName = queueName;
	 }
	 
	 public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey ;
	 }
	 
	 public void closeConnection() {
		 if (connection != null) {
			 try {
				 connection.close();
			 }
			 catch (IOException ignore) {}
		 }
	 }
	 
	@Override
	protected void finalize() throws Throwable {
			destroy();
	}
	
	public void destroy() {
		if(channel != null) {
			try {
				channel.close(); 
			} catch(Exception e) {}
		}
		if(connection != null) {
			try {
				connection.close();
			} catch(Exception e) {
			}
		}
	}
	 
	 public void processFile()  {
		 while (true) {
			 try {
				 // Get the next message in the queue
				 Delivery delivery = consumer.nextDelivery();
				 logger.log(Level.DEBUG, "FileConsumer:: New message to process");
				 if (delivery == null)
				 {
					 if (channel.isOpen() == false) { 
						 throw new ShutdownSignalException(false, false, delivery, delivery); }
				 }
				 else
				 {
					 logger.log(Level.DEBUG, "FileConsumer:: Message has data");
					 String message = new String(delivery.getBody());  
					 if(message != null & (((message = message.trim()).length())>0)) {
						 logger.log(Level.INFO, "FileConsumer:: Process message: "+message);
						 if (message.contains(ConsumerConstants.Rabbitmq.MSG_TOKEN_SEPARATOR)) {
							 String[] tokens = message.split(ConsumerConstants.Rabbitmq.MSG_TOKEN_SEPARATOR);
							 int eventId=0;

							 if (tokens.length != ConsumerConstants.Rabbitmq.MSG_TOTAL_TOKEN) {
								 logger.log(Level.INFO, "FileConsumer::Invalid message format: "+ message);
								 channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);  // ignore the message by ACK so that it will not clog in the Q
							 }
							 else { 
								 try {
									 eventId = Integer.parseInt(tokens[1]);
								 } catch (NumberFormatException e) {
									 channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);  // ignore the message by ACKing so that it will not clog the Q
									 continue;
								 }
								 if(tokens[0].equals(ConsumerConstants.Rabbitmq.MSG_JOB)) {	// Job message

									 String url = URLConstants.WS_URL_ENDPOINT + URLConstants.WEB_SERVICE_PATH + URLConstants.JOB_PROCESS_PATH;
									 ProcessJobRequest jobRequest = new ProcessJobRequest();
									 jobRequest.setJobId(eventId);				 								 
									 Response response = RestServiceClientHelper.invokeRestService(
											 url,
											 jobRequest,
											 null,
											 MediaType.APPLICATION_JSON_TYPE,
											 HttpMethodEnum.POST);
									 // if response code = 200, ack the message so that it will not be processed more than once 
									 if(response.getStatus() == Response.Status.OK.getStatusCode()) 
										 channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);   // ack one message
									 else
										 // if response is not 200, re-queue so the message will be reprocessed
										 channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);  // re-queue single message
								 }
								 else if(tokens[0].equals(ConsumerConstants.Rabbitmq.MSG_TASK)) {
									 String url = URLConstants.WS_URL_ENDPOINT + URLConstants.WEB_SERVICE_PATH + URLConstants.TASK_PROCESS_PATH;
									 ProcessTaskRequest taskRequest = new ProcessTaskRequest();
									 taskRequest.setTaskId(eventId);
									 Response response = RestServiceClientHelper.invokeRestService(
											 url,
											 taskRequest,
											 null,
											 MediaType.APPLICATION_JSON_TYPE,
											 HttpMethodEnum.POST);
									 // ack the message so that it will not be processed more than once
									 if(response.getStatus() == Response.Status.OK.getStatusCode()) 
										 channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);   // ack one message
									 else
										 // if response is not 200, re-queue so the message will be reprocessed
										 channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);  // re-queue single message
								 }
								 else 
									 channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);  // re-queue single message
							 } 
						 }
						 else {
						    logger.log(Level.INFO, "FileConsumer::Message format invalid :"  + message);
						    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
						 }
					 }
					 else {
						 // ignore the message
						 logger.log(Level.INFO, "FileConsumer::Message: Junk message");
						 channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
					 }
				 }	
			 } 
			 catch (ShutdownSignalException e) {
				 logger.log(Level.WARN, "Consumer:: " + FileConsumer.class.getName() + " Shutodwon signal received.");
				 break;
			 } 
			 catch (ConsumerCancelledException e) {
				 logger.log(Level.WARN, "Consumer:: " + FileConsumer.class.getName() + " Consumer cancelled exception: " + e.getMessage()); 
				 break;
			 } 
			 catch (InterruptedException e) {
				 logger.log(Level.WARN, "Consumer:: " + FileConsumer.class.getName() + " Interruption exception: " + e.getMessage()); 
				 continue;
			 } 
			 catch (IOException e) {
				 logger.log(Level.WARN, "Consumer:: " + FileConsumer.class.getName() + " Could not ack message: " + e.getMessage()); 
				 break;
			 }
			 catch (Exception e){
				 logger.log(Level.WARN, "Consumer:: " + FileConsumer.class.getName() + " Could not ack message: " + e.getMessage()); 
				 break;
			 }
		 }
	  }
	 
	 private Response processMessage(String url, String eventType, BaseRequest request)
	 {
		 if(eventType.equals(ConsumerConstants.Rabbitmq.MSG_JOB))
			request = (ProcessJobRequest)request;
		 else if(eventType.equals(ConsumerConstants.Rabbitmq.MSG_TASK))
			request = (ProcessTaskRequest)request;
		 Response response = RestServiceClientHelper.invokeRestService(
				 url,
				 request,
				 null,
				 MediaType.APPLICATION_JSON_TYPE,
				 HttpMethodEnum.POST);
		 return response;
	 }
	 
	 public static void main(String[] args) {
		 FileConsumer consumer = null;
		 try {
			 consumer = new FileConsumer();
			 consumer.setChannelNumber(ConsumerConstants.Rabbitmq.RABBIT_AMQP_CHANNEL_NUMBER);
			 consumer.setExchangeName(ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME);
			 consumer.setQueueName(ConsumerConstants.Rabbitmq.RABBIT_AMQP_QUEUE_NAME);
			 consumer.setDurableExchange(true);
			 consumer.setDurableQueue(true);
			 consumer.setRoutingKey(ConsumerConstants.Rabbitmq.RABBIT_AMQP_ROUTING_KEY);
			 consumer.setConsumerTag(ConsumerConstants.Rabbitmq.RABBIT_AMQP_CONSUMER_TAG);
			 consumer.setPrefetchMsgCount(ConsumerConstants.Rabbitmq.RABBIT_AMQP_EVENT_PREFETCH_COUNT);
			 consumer.setAutoAck(false);
			 consumer.init();
			 if(consumer.isChannelActive()) {
				 consumer.processFile();
			 }
		 } 
		 catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 if(consumer != null) {
				 consumer.closeConnection();
			 }
		 }
		  }

}
