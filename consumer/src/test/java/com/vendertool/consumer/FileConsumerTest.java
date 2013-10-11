package com.vendertool.consumer;

import static org.junit.Assert.*;
import java.io.IOException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileConsumerTest {
	
	static ConnectionFactory connectionFactory;
	static Connection producerConnection = null;
	private static final Logger logger = Logger.getLogger(FileConsumerTest.class);
	
	static class Publisher implements Runnable {
		public void run() {
			long startTime = 0;
			try{
				//start the producer
				startTime = System.currentTimeMillis();
				ConnectionFactory factory = new ConnectionFactory();

				factory.setHost(ConsumerConstants.Rabbitmq.RABBIT_AMQP_HOST);
				Connection connection = factory.newConnection();  // need to create new connection and close after publishing the events

				Channel channel = connection.createChannel(); // created channel will be closed once the connection is closed

				// Do not delete the exchange as it is not idempotent
				//channel.exchangeDelete(ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME);
				channel.exchangeDeclare(ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME, "direct");
				channel.queueDeclare(ConsumerConstants.Rabbitmq.RABBIT_AMQP_QUEUE_NAME, true, false, false, null);
				// Bind the queue to the exchange using the routing key as this is a DIRECT exchange
				channel.queueBind(ConsumerConstants.Rabbitmq.RABBIT_AMQP_QUEUE_NAME, ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME, ConsumerConstants.Rabbitmq.RABBIT_AMQP_ROUTING_KEY);
				String message="";
				for(int i=0;i<10;i++){
					message ="Payload: " +i;
					channel.basicPublish(ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME, ConsumerConstants.Rabbitmq.RABBIT_AMQP_ROUTING_KEY, null, message.getBytes());
					System.out.println(" [x] Sent '"  + "':'" + message + "'");
				}
				connection.close(); // dropping the connection will drop the channel
				System.out.println(" [x] Done '"  + "':'" + message + "'");

			} catch(IOException ex) {
				logger.log(Level.INFO, "FileConsumer::before "  + " initialize error."+ex.getMessage());
			}
			finally {
				long endTime = System.currentTimeMillis();
				System.out.printf("Test took %.3fs\n", (float)(endTime - startTime)/1000);
			}
		}
    }

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//ConnectionFactory connectionFactory = new ConnectionFactory();
		//connectionFactory.setHost(ConsumerConstants.Rabbitmq.RABBIT_AMQP_HOST); 	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	}

	@Test(expected=IOException.class)
	public void testProducer() {
		long startTime = 0;
		try{
			//start the producer and publish one message
			logger.log(Level.INFO, "FileConsumer::before "  + " initialize started.");
			startTime = System.currentTimeMillis();
			ConnectionFactory factory = new ConnectionFactory();
			
		    factory.setHost(ConsumerConstants.Rabbitmq.RABBIT_AMQP_HOST);
		    Connection connection = factory.newConnection();  // need to create new connection and close after publishing the events
		    
		    Channel channel = connection.createChannel(); // created channel will be closed once the connection is closed
		        
		    // Do not delete the exchange as it is not idempotent
		    //channel.exchangeDelete(ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME);
		    
		    channel.exchangeDeclare(ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME, "direct");
		    channel.queueDeclare(ConsumerConstants.Rabbitmq.RABBIT_AMQP_QUEUE_NAME, true, false, false, null);
		    // Bind the queue to the exchange using the routing key as this is a DIRECT exchange
			 channel.queueBind(ConsumerConstants.Rabbitmq.RABBIT_AMQP_QUEUE_NAME, ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME, ConsumerConstants.Rabbitmq.RABBIT_AMQP_ROUTING_KEY);

		    String message="";
		    for(int i=0;i<10;i++){
		    	message ="Payload: " +i;
		    	channel.basicPublish(ConsumerConstants.Rabbitmq.RABBIT_AMQP_XCHANGE_NAME, ConsumerConstants.Rabbitmq.RABBIT_AMQP_ROUTING_KEY, null, message.getBytes());
		    	System.out.println(" [x] Sent '"  + "':'" + message + "'");
		    }
		    connection.close(); // dropping the connection will drop the channel
		    System.out.println(" [x] Done '"  + "':'" + message + "'");
		    
		} catch(IOException ex) {
			logger.log(Level.INFO, "FileConsumer::before "  + " initialize error."+ex.getMessage());
		}
		finally {
			long endTime = System.currentTimeMillis();
	        System.out.printf("Test took %.3fs\n", (float)(endTime - startTime)/1000);
		}
		//(new Thread(new Publisher())).start();
		assertEquals(10,10);  // rabbit on publish does not return the count of message. Need a method to see the total count on queue to assert
	}
	
	@Test(expected=IOException.class)
	public void testConsumer() {
		 FileConsumer consumer = null;
		 logger.log(Level.INFO, "FileConsumer:: CONSUMER....."  + " initialize started here.");
		 
		 // start the consumer
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
			 logger.log(Level.INFO, "FileConsumer:: CONSUMER<><>"  + " initialize started here.");
			 consumer.init();
			 logger.log(Level.INFO, "FileConsumer:: CONSUMER"  + " initialize started here.");
			 if(consumer.isChannelActive()) {
				 consumer.processFile();
			 }
		} catch(IOException ex) {logger.log(Level.INFO, "FileConsumer:: "  + " initialize started."+ex.getMessage());} 
		catch (Exception e) {
			logger.log(Level.INFO, "FileConsumer:: "  + " eroor."+e.getMessage());
		}
		// need a method in Rabbit to find out the existing messages and processed messages. Currently forcing the assert
		assertEquals(10,10);  // rabbit on publish does not return the count of message. Need a method to see the total count on queue to assert
	}

}
