package com.vendertool.sitewebapp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vendertool.sharedapp.RestServiceClientHelper;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sharedtypes.rnr.UploadsResponse;
import com.vendertool.sharedtypes.rnr.fps.GetJobsResponse;
import com.vendertool.sharedtypes.rnr.fps.JobDetails;
import com.vendertool.sitewebapp.common.ContainerBootstrapContext;
import com.vendertool.sitewebapp.common.WebURLConstants;
import com.vendertool.sitewebapp.util.MockDataUtil;

@Controller
public class UploadsController {
	
	private static final Logger logger = Logger.getLogger(UploadsController.class);
	
	@RequestMapping(value=WebURLConstants.UPLOADS, method=RequestMethod.GET)
	public String getUploadsView(ModelMap modelMap, HttpServletRequest req) {
	//public String getUploadsView(ModelMap modelMap, Principal principal) {
		logger.info("getUploadsView controller invoked");

		String email = ContainerBootstrapContext.getSignedInEmail();
        String hostName = RestServiceClientHelper.getServerURL(req);
		String url = hostName + WebURLConstants.WEB_SERVICE_PATH + 
				WebURLConstants.JOB_DETAILS_PATH + WebURLConstants.QUERY_START + 
				WebURLConstants.USERNAME_KEY + WebURLConstants.PARAM_KEY_VALUE_SEPARATOR + email;
		
		Response serviceRes = RestServiceClientHelper
				.invokeRestService(url, null, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.GET);
		
		GetJobsResponse getJobsResponse = serviceRes.readEntity(GetJobsResponse.class);
		List<JobDetails> ljDetails = getJobsResponse.getJobs();
		
		for (JobDetails jobDetails : ljDetails) {
			System.out.println("JobDetails :" + jobDetails.getJob().getTitle());
			System.out.println("JobDetails :" + jobDetails.getJob().getReqFileGroupId());
			System.out.println("JobDetails :" + jobDetails.getJob().getJobId());
			System.out.println("JobDetails :" + jobDetails.getJob().getStatus());
			for (File file : jobDetails.getFiles()) {
				System.out.println("File :"+ file.getFileName());
			}
		}
		
		logger.info("getUploadsView controller invoked");

		// TODO: principal is throwing error
		//modelMap.addAttribute("email", principal.getName());
		modelMap.addAttribute("email", "ted@gmail.com");
		modelMap.addAttribute("uploadsResponse", MockDataUtil.getUploadsResponse());
		modelMap.addAttribute("errorResponse", new ErrorResponse());
		
		// Add JSON for Angular
		/*
		try {
			ObjectMapper mapper = new ObjectMapper();
			String modelMapJson= mapper.writeValueAsString(modelMap);
			modelMap.put("modelMapJson", modelMapJson);
		}
		catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new VTRuntimeException("Cannot convert modelMap to json");
		}*/
		
		return "uploads/uploads";
	}
	
	@RequestMapping(value=WebURLConstants.UPLOADS_RESPONSE, method=RequestMethod.GET)
	public @ResponseBody UploadsResponse getUploadsResponse(ModelMap modelMap, Principal principal) {
		logger.info("getUploadsResponse controller invoked");
		
		return MockDataUtil.getUploadsResponse();
	}

	/******************************************
	 * 
	 * Get partial pages for Angular
	 * 
	 ******************************************/
	@RequestMapping(value = "accounthub/uploads/partial/fileList", method = RequestMethod.GET)
	public String getFilesPartial() {
		logger.info("getFilesPartial controller invoked");
		return "uploads/partial/fileList";
	}
	
}
