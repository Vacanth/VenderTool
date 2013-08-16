package com.vendertool.sitewebapp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileUploadController {

	private static final Logger logger = Logger.getLogger(FileUploadController.class);

	@RequestMapping(value = "fileUpload", method = RequestMethod.GET)
	public String getFIleUploadPage(ModelMap modelMap) {
		//TODO validate session
		logger.info("fileUpload GET controller invoked");

		
		return "fileupload";
	}
}