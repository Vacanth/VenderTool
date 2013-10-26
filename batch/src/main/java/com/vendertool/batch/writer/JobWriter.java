package com.vendertool.batch.writer;


import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.vendertool.batch.model.JobModel;

public class JobWriter implements ItemWriter<JobModel> {
	@Autowired
	
	public void write(List<? extends JobModel> items) throws Exception {
	}
}