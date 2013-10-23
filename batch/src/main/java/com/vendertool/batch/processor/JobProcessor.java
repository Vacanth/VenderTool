package com.vendertool.batch.processor;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import com.vendertool.batch.model.*;
import com.vendertool.sharedapp.RestServiceClientHelper;
import com.vendertool.sharedapp.URLConstants;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;

public class JobProcessor implements ItemProcessor<Job, Job>{

	public Job process(Job value) throws Exception{
		System.out.println("In proicessor-->"+ value.getStatus() + "::"+ value.getJob_id());	
		
		// fps call to recreate the job
		 String url = URLConstants.WS_URL_ENDPOINT + URLConstants.WEB_SERVICE_PATH + URLConstants.JOB_PROCESS_PATH;
		 ProcessJobRequest jobRequest = new ProcessJobRequest();
		 jobRequest.setJobId(value.getJob_id());
		 Response serviceRes = RestServiceClientHelper.invokeRestService(
				 url,
				 jobRequest,
				 null,
				 MediaType.APPLICATION_JSON_TYPE,
				 HttpMethodEnum.POST);
				 
		Job job=new Job();	
		job.setJob_id(value.getJob_id());
		return job;
	}
}

