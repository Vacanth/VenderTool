package com.vendertool.sharedtypes.rnr.fps;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.rnr.BaseResponse;

@XmlRootElement
public class GetJobsResponse extends BaseResponse {
	private List<JobDetails> jobs;

	public List<JobDetails> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobDetails> lJobs) {
		if (lJobs != null) {
			this.jobs = new ArrayList<JobDetails>(lJobs.size());
			this.jobs.addAll(lJobs);
		}
	}
}
