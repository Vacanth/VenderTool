package com.vendertool.fps.rmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.vendertool.common.SpringApplicationContextUtils;

public class AWSRmqMetadataFactory {

	private Map<AWSRmqQueueType, AWSRmqMetadata> queueDataMap;
	private ApplicationContext ctx;

	private AWSRmqMetadataFactory() {
		queueDataMap = new HashMap<AWSRmqQueueType, AWSRmqMetadata>();
		ctx = SpringApplicationContextUtils.getApplicationContext();
		init();
	}

	private static class AWSRmqMetadataFactorySingletonHelper {
		private static final AWSRmqMetadataFactory INSTANCE = new AWSRmqMetadataFactory();
	}

	// To make Singleton *** End ***
	public static AWSRmqMetadataFactory getInstance() {
		return AWSRmqMetadataFactorySingletonHelper.INSTANCE;
	}

	public void init() {
		register(AWSRmqQueueType.PRODUCT);
		register(AWSRmqQueueType.LISTING);
		register(AWSRmqQueueType.JOB);
		register(AWSRmqQueueType.TASK);
	}

	public AWSRmqMetadata getQueueMetaData(AWSRmqQueueType queueType) {
		AWSRmqMetadata metaData = null;
		if (queueType != null) {
			metaData = queueDataMap.get(queueType);
		}
		return metaData;
	}

	private void register(AWSRmqQueueType queueType) {
		AWSRmqMetadata metaDate = getBean(queueType);
		if (metaDate != null) {
			queueDataMap.put(queueType, metaDate);
		}
	}

	private AWSRmqMetadata getBean(AWSRmqQueueType queueType) {
		if (queueType == null) {
			return null;
		}
		return (AWSRmqMetadata) ctx.getBean(queueType.getBeanName());
	}
}