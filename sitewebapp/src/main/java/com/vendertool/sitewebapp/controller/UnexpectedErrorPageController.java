package com.vendertool.sitewebapp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UnexpectedErrorPageController {

	private static final Logger logger = Logger.getLogger(UnexpectedErrorPageController.class);

	@RequestMapping(value="unexpectedError", method=RequestMethod.GET)
	public String getUnexpectedErrorView(ModelMap modelMap) {
		logger.info("register GET controller invoked");
		
		return "unexpectedError";
	}
}