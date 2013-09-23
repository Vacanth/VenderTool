package com.vendertool.sharedtypes.rnr.fps;

import com.vendertool.sharedtypes.rnr.BaseRequest;

public class GetJobsRequest extends BaseRequest {

	private long[] jobIds;
	private String emailId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long[] getFiles() {
		return jobIds;
	}

	public void setJobIds(long[] jobIds) {
		this.jobIds = jobIds;
	}
}
