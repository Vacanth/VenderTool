package com.vendertool.sharedtypes.rnr.fps;

import java.util.ArrayList;
import java.util.List;

import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.core.fps.Job;

public class JobDetails {
	private Job job;
	private List<File> uploadedFiles;
	private List<File> processedFiles;
	
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public List<File> getUploadedFiles() {
		return uploadedFiles;
	}
	
	public List<File> getProcessedFiles() {
		return processedFiles;
	}
	
	public void setUploadedFiles(List<File> iFiles) {
		this.uploadedFiles = new ArrayList<File>(iFiles.size());
		this.uploadedFiles.addAll(iFiles);
	}
	
	public void setProcessedFiles(List<File> iFiles) {
		this.processedFiles = new ArrayList<File>(iFiles.size());
		this.processedFiles.addAll(iFiles);
	}	
}

