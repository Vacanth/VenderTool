package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.fps.FPSJobStatusEnum;
import com.vendertool.sharedtypes.rnr.BaseResponse;

@XmlRootElement
public class GetJobStatusResponse extends BaseResponse {
	private FPSJobStatusEnum jobStatus;
	
	public void setJobStatus(FPSJobStatusEnum jobStatus) {
		this.jobStatus = jobStatus;
	}

	public FPSJobStatusEnum getJobStatus() {
		return jobStatus;
	}
}
