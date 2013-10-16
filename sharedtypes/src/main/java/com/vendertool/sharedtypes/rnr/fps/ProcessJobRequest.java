package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;
import com.vendertool.sharedtypes.rnr.BaseRequest;

@XmlRootElement
public class ProcessJobRequest extends BaseRequest {
	private long jobId;
	
	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
}
