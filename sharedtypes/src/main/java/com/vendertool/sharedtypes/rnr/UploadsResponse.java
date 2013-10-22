package com.vendertool.sharedtypes.rnr;


import java.util.List;
import java.util.Map;

import com.vendertool.sharedtypes.core.PaginationOutput;
import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.core.fps.Job;

public class UploadsResponse extends BaseResponse{

	private List<Job> jobs;
	private PaginationOutput paginationOutput;
	
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public PaginationOutput getPaginationOutput() {
		return paginationOutput;
	}
	public void setPaginationOutput(PaginationOutput paginationOutput) {
		this.paginationOutput = paginationOutput;
	}
}
