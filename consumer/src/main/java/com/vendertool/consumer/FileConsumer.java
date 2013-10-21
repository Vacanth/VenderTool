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
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;

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
		
		new ClassPathXmlApplicationContext("consumer-app-context.xml");
		
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
				 // Create transaction
				 //channel.txSelect();
				 Delivery delivery = consumer.nextDelivery();
				 if (delivery == null)
				 {
					 //channel.txRollback();
					 if (channel.isOpen() == false) { 
						 throw new ShutdownSignalException(false, false, delivery, delivery); }
				 }
				 else
				 {
					 String message = new String(delivery.getBody());  // if message is missign, rollback
					 message = message.trim();
					 logger.log(Level.INFO, "FileConsumer:: "  + " message to process..."+message);
					 if (message.contains(ConsumerConstants.Rabbitmq.MSG_TOKEN_SEPARATOR)) {
						 String[] result = message.split(ConsumerConstants.Rabbitmq.MSG_TOKEN_SEPARATOR);
						 if (result.length != ConsumerConstants.Rabbitmq.MSG_TOTAL_TOKEN) {
						     logger.log(Level.INFO, "FileConsumer::Message:"  + message + " not in correct format");
						     channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
						     // channel.rollback);
						 }
						 else if(result[0].equals(ConsumerConstants.Rabbitmq.MSG_JOB)) {
							 String hostName = "http://localhost:8080";
							 String url = hostName + URLConstants.WEB_SERVICE_PATH + URLConstants.JOB_PROCESS_PATH;
								ProcessJobRequest jobRequest = new ProcessJobRequest();
								jobRequest.setJobId(356);
								Response serviceRes = RestServiceClientHelper.invokeRestService(
										url,
										jobRequest,
										null,
										MediaType.APPLICATION_JSON_TYPE,
										HttpMethodEnum.POST);
								// if response code = 201, ack
							 // ack the message so that it will not be processed more than once
							 channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
							 //channel.txCommit();	// Commit the transaction
						 }
					 } 
					 else {
						    logger.log(Level.INFO, "FileConsumer::Message:"  + message + " not in correct format");
						    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
						    // channel.rollback(); 
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
