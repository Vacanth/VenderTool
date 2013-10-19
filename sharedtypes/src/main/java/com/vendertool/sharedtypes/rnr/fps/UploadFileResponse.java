package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.rnr.BaseResponse;

@XmlRootElement
public class UploadFileResponse extends BaseResponse {
	private boolean success;
	private long jobId;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public long getJobId() {
		return this.jobId;
	}

}
