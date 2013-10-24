package com.vendertool.batch.processor;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendertool.batch.dao.JobDao;
import com.vendertool.batch.model.*;
import com.vendertool.sharedapp.RestServiceClientHelper;
import com.vendertool.sharedapp.URLConstants;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;

public class JobProcessor implements ItemProcessor<Job, Job>{
	
	private static final Logger logger = Logger.getLogger(JobProcessor.class);
	
	public Job process(Job job) throws Exception{
		logger.log(Level.INFO, "BatchJobProcessor:: processing jobID "+job.getJob_id());
		
		 String url = URLConstants.WS_URL_ENDPOINT + URLConstants.WEB_SERVICE_PATH + URLConstants.JOB_PROCESS_PATH;
		 ProcessJobRequest jobRequest = new ProcessJobRequest();
		 jobRequest.setJobId(job.getJob_id());
		 Response response = RestServiceClientHelper.invokeRestService(
				 url,
				 jobRequest,
				 null,
				 MediaType.APPLICATION_JSON_TYPE,
				 HttpMethodEnum.POST);
		 if(response.getStatus() == Response.Status.OK.getStatusCode()) 
		 {
			 logger.log(Level.INFO, "BatchJobProcessor:: OK......"+url);
				
			 // if job event created, call the writer to update the status
			 Job writerJob=new Job();	
			 writerJob.setJob_id(job.getJob_id());
			 return writerJob;
			 
		 }
		 else
			 return null;
	}
}

