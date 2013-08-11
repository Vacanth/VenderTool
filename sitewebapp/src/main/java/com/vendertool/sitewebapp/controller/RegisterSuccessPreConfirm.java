package com.vendertool.sitewebapp.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class RegisterSuccessPreConfirm {

	private static final Logger logger = Logger.getLogger(RegisterSuccessPreConfirm.class);

	@RequestMapping(value="registersuccesspreconfirm", method=RequestMethod.GET)
	public String getUnexpectedExcepView(ModelMap modelMap) {
		logger.info("register GET controller invoked");
		
		return "registersuccesspreconfirm";
	}
}