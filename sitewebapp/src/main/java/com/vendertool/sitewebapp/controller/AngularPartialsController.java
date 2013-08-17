package com.vendertool.sitewebapp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Used by AngularJS to return partial templates.
 * 
 * @author ted
 *
 */
@Controller
public class AngularPartialsController {
	private static final Logger logger = Logger.getLogger(AngularPartialsController.class);

	@RequestMapping(value = "partials", method = RequestMethod.GET)
	public String getPartial(@RequestParam(value="n", required=true) String pageName, ModelMap modelMap) {
		logger.info("AngularPartials GET controller invoked");

		return "partials/" + pageName;
	}
}