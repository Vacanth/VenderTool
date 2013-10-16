package com.vendertool.fps.fileupload.helper;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FPSBaseEvent {
	private String queueName;
	private String hostName;
	private String exchange;
	private String routingKey;
	private String qType;
	
	public FPSBaseEvent(String qName, String hName, String xchange, String rKey, String type) {
		queueName = qName;
		hostName = hName;
		exchange = xchange;
		routingKey = rKey;
		qType = type;
	}
	
	public void sendEvent(String payload) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(hostName);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(exchange, qType);
			channel.queueDeclare(queueName, true, false, false, null);
			channel.queueBind(queueName, exchange, routingKey);
			
			//channel.queueBind(queueName, "uploadfileX", "uploadfiles");
			//channel.exchangeDeclare("uploadfileX", "direct");
			
			channel.basicPublish("", queueName, null, payload.getBytes());
			channel.close();
			connection.close();
		} catch (IOException io) {
			System.out.println("Error Message: not able to publish the event " + io.getMessage());
		}
	}
}
