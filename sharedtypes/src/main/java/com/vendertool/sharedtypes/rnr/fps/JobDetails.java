package com.vendertool.sharedtypes.rnr.fps;

import java.util.ArrayList;
import java.util.List;

import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.core.fps.Job;

public class JobDetails {
	private Job job;
	private List<File> files;
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> iFiles) {
		this.files = new ArrayList<File>(iFiles.size());
		this.files.addAll(iFiles);
	}
	
}

