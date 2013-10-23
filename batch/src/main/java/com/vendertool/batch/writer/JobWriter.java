package com.vendertool.batch.writer;


import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.vendertool.batch.dao.JobDao;
import com.vendertool.batch.model.Job;


public class JobWriter implements ItemWriter<Job> {

	StringBuilder sb = new StringBuilder();
	@Autowired
	private JobDao jobDao;
	
	public void write(List<? extends Job> items) throws Exception {
		
		for(Job job: items){
			jobDao.save(job);
		}
	}
	
	public JobDao getJobDao() {
		return jobDao;
	}

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}
}