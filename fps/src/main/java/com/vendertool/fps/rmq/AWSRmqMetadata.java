package com.vendertool.fps.rmq;

public class AWSRmqMetadata {

	private String queueName;
	private String queueHost;
	private String exchange;
	private String routingKey;
	private String queueType;
	
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getQueueHost() {
		return queueHost;
	}
	public void setQueueHost(String queueHost) {
		this.queueHost = queueHost;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getRoutingKey() {
		return routingKey;
	}
	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	public String getQueueType() {
		return queueType;
	}
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
}
