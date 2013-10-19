package com.vendertool.sitewebapp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HttpErrorController {
	private static final Logger logger = Logger.getLogger(NgModulesController.class);
	
	@RequestMapping(value = "httpError/{errorCode}", method = RequestMethod.GET)
	public String getHttpErrorView(Model model, @PathVariable String errorCode) {
		logger.info("getHttpErrorView controller invoked");
		
		model.addAttribute("errorCode", errorCode);
		return "errors/httpError";
	}
}
