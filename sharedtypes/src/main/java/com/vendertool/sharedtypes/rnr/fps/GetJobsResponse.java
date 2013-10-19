package com.vendertool.sharedtypes.rnr.fps;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.fps.Job;
import com.vendertool.sharedtypes.rnr.BaseResponse;

@XmlRootElement
public class GetJobsResponse extends BaseResponse {
	private List<Job> jobs;

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> lJobs) {
		if (lJobs != null) {
			this.jobs = new ArrayList<Job>(lJobs.size());
			this.jobs.addAll(lJobs);
		}
	}
}
