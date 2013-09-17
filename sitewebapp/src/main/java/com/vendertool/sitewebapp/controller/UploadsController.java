package com.vendertool.sitewebapp.controller;

import java.security.Principal;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendertool.sharedtypes.core.FileUploadDataModel;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sitewebapp.common.URLConstants;
import com.vendertool.sitewebapp.util.MockDataUtil;

@Controller
public class UploadsController {
	
	private static final Logger logger = Logger.getLogger(UploadsController.class);
	
	@RequestMapping(value=URLConstants.UPLOADS, method=RequestMethod.GET)
	public String getUploadsView(ModelMap modelMap, Principal principal) {
		logger.info("getUploadsView controller invoked");

		// TODO: principal is throwing error
		//modelMap.addAttribute("email", principal.getName());
		modelMap.addAttribute("email", "ted@gmail.com");
		modelMap.addAttribute("uploadsResponse", MockDataUtil.getUploadsResponse());
		modelMap.addAttribute("errorResponse", new ErrorResponse());
		
		// Add JSON for Angular
		try {
			ObjectMapper mapper = new ObjectMapper();
			String modelMapJson= mapper.writeValueAsString(modelMap);
			modelMap.put("modelMapJson", modelMapJson);
		}
		catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new VTRuntimeException("Cannot convert modelMap to json");
		}
		
		return "uploads/uploads";
	}

	/******************************************
	 * 
	 * Get partial pages for Angular
	 * 
	 ******************************************/
	@RequestMapping(value = "accounthub/uploads/partial/files", method = RequestMethod.GET)
	public String getFilesPartial() {
		logger.info("getFilesPartial controller invoked");
		return "uploads/partial/files";
	}
	
}
