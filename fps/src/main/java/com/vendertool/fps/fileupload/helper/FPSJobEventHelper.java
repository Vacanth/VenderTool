package com.vendertool.fps.fileupload.helper;

import com.vendertool.fps.rmq.AWSRmqMetadata;
import com.vendertool.fps.rmq.AWSRmqMetadataFactory;
import com.vendertool.fps.rmq.AWSRmqQueueType;

public class FPSJobEventHelper extends FPSBaseEvent {
	private static FPSJobEventHelper s_self = null;
	
	private FPSJobEventHelper(String hostName,String queueName, String xChange, String rKey, String qType) {
		super(queueName, hostName, xChange, rKey, qType);
	}

	public synchronized static FPSJobEventHelper getInstance() {
		if (s_self == null) {
			AWSRmqMetadata rmqMetaData = AWSRmqMetadataFactory.getInstance().
					getQueueMetaData(AWSRmqQueueType.JOB);
			s_self = new FPSJobEventHelper(rmqMetaData.getQueueName(),rmqMetaData.getQueueHost(),
											rmqMetaData.getExchange(), rmqMetaData.getRoutingKey(),
											rmqMetaData.getQueueType());
		}
		return s_self;
	}

	public void sendEvent(String payload) {
		super.sendEvent(payload);
	}
}
