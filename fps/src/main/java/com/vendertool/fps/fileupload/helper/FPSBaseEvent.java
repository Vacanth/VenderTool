package com.vendertool.fps.fileupload.helper;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FPSBaseEvent {
	private String queueName;
	private String hostName;
	private static ConnectionFactory factory = null;
	
	public FPSBaseEvent(String qName, String hName) {
		queueName = qName;
		hostName = hName;
		factory = new ConnectionFactory();
		factory.setHost(hostName);
	}
	
	public void sendEvent(String payload) {
		try {
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(queueName, false, false, false, null);
		channel.basicPublish("", queueName, null, payload.getBytes());
		channel.close();
		connection.close();
		} catch (IOException io) {
			System.out.println("Error Message: not able to publish the event " + io.getMessage());
		}
	}
}
