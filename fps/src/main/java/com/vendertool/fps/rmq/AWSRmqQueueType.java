package com.vendertool.fps.rmq;

public enum AWSRmqQueueType {
	PRODUCT("productRmqData"), LISTING("listingRmqData"), JOB("jobRmqData"), TASK("taskRmqData");

	private String beanName;

	private AWSRmqQueueType(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}