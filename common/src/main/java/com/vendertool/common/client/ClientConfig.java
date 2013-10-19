package com.vendertool.common.client;

public class ClientConfig {

	private String endPointURL;
	private long timeOutInMilliSeconds;// in milli seconds

	public String getEndPointURL() {
		return endPointURL;
	}

	public void setEndPointURL(String endPointURL) {
		this.endPointURL = endPointURL;
	}

	public long getTimeOutInMilliSeconds() {
		return timeOutInMilliSeconds;
	}

	public void setTimeOutInMilliSeconds(long timeOutInMilliSeconds) {
		this.timeOutInMilliSeconds = timeOutInMilliSeconds;
	}
}