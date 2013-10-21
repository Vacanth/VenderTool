package com.vendertool.consumer;

public class ConsumerConstants {
	public static class Rabbitmq{
		public static final String RABBIT_AMQP_HOST = "ec2-54-205-235-107.compute-1.amazonaws.com";
		public static final String RABBIT_AMQP_HOST_USERNAME = "guest";
		public static final String RABBIT_AMQP_HOST_PASSWORD = "guest";
		public static final String RABBIT_AMQP_VIRTUAL_HOST = "virtualHost";
		public static final int    RABBIT_AMQP_HEARTBEAT = 0;
		public static final String RABBIT_AMQP_QUEUE_NAME = "uploadfileQ";
		public static final String RABBIT_AMQP_XCHANGE_NAME = "uploadfileX";
		public static final String RABBIT_AMQP_ROUTING_KEY= "uploadfiles";
		public static final int    RABBIT_AMQP_CHANNEL_NUMBER = 5000;
		public static final String RABBIT_AMQP_CONSUMER_TAG = "fpConsumer";
		public static final int    RABBIT_AMQP_EVENT_PREFETCH_COUNT = 1;
		public static final String MSG_JOB = "jobId";
		public static final String MSG_TASK = "taskId";
		public static final String MSG_TOKEN_SEPARATOR = "=";
		public static final int MSG_TOTAL_TOKEN = 2;
	}
}
