package com.vendertool.fps.fileupload.helper;

import com.vendertool.fps.rmq.AWSRmqMetadata;
import com.vendertool.fps.rmq.AWSRmqMetadataFactory;
import com.vendertool.fps.rmq.AWSRmqQueueType;

public class FPSProductEventHelper extends FPSBaseEvent {
	private static FPSProductEventHelper s_self = null;
	
	private FPSProductEventHelper(String hostName,String queueName, String xChange, String rKey, String qType) {
		super(queueName, hostName, xChange, rKey, qType);
	}

	public synchronized static FPSProductEventHelper getInstance() {
		if (s_self == null) {
			AWSRmqMetadata rmqMetaData = AWSRmqMetadataFactory.getInstance().
					getQueueMetaData(AWSRmqQueueType.LISTING);
			s_self = new FPSProductEventHelper(rmqMetaData.getQueueName(),rmqMetaData.getQueueHost(),
					rmqMetaData.getExchange(), rmqMetaData.getRoutingKey(),
					rmqMetaData.getQueueType());
		}
		return s_self;
	}

	public void sendEvent(String payload) {
		super.sendEvent(payload);
	}
}
