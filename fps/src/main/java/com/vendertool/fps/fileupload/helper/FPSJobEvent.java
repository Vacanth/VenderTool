package com.vendertool.fps.fileupload.helper;

import com.vendertool.common.SpringApplicationContextUtils;
import com.vendertool.fps.rmq.AWSRmqMetadata;
import com.vendertool.fps.rmq.AWSRmqMetadataFactory;
import com.vendertool.fps.rmq.AWSRmqQueueType;

public class FPSJobEvent extends FPSBaseEvent {
	private static String queueName = null;
	private static String hostName = null;
	private static String xChange = null;
	private static String routingKey = null;
	private static String qType = null;

	static {
		/*AWSRmqMetadata rmqMetaData = AWSRmqMetadataFactory.getInstance().
				getQueueMetaData(AWSRmqQueueType.JOB);  */
		
		AWSRmqMetadata rmqMetaData = 
				(AWSRmqMetadata)SpringApplicationContextUtils.getApplicationContext().
							getBean(AWSRmqQueueType.JOB.getBeanName());
		queueName = rmqMetaData.getQueueName();
		hostName = rmqMetaData.getQueueHost();
		xChange = rmqMetaData.getExchange();
		routingKey = rmqMetaData.getRoutingKey();
		qType = rmqMetaData.getQueueType();
	}
	
	private long jobId;

	public FPSJobEvent() {
		super(queueName, hostName, xChange,routingKey,qType);
	}
	
	public FPSJobEvent(String qName, String hName, String exchange, String rKey, String type) {
		super(qName,hName, exchange, rKey, type);
	}
	
	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
	public String getPayload() {
		return "jobId="+ jobId;
	}
	
	public void sendEvent() {
		if (jobId !=0) {
			sendEvent(getPayload());
		}
	}

	public void sendEvent(String payload) {
		super.sendEvent(payload);
	}
}