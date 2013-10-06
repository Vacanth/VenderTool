package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.rnr.BaseRequest;

@XmlRootElement
public class GetJobsRequest extends BaseRequest {

	private long[] jobIds;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long[] getFiles() {
		return jobIds;
	}

	public void setJobIds(long[] jobIds) {
		this.jobIds = jobIds;
	}
}
