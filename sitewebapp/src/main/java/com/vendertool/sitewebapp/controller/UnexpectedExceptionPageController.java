package com.vendertool.sitewebapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.vendertool.sitewebapp.util.MenuBuilder;

@Controller
public class UnexpectedExceptionPageController {

	private static final Logger logger = Logger.getLogger(UnexpectedExceptionPageController.class);

	@RequestMapping(value="unexpectedException", method=RequestMethod.GET)
	public String getUnexpectedExcepView(ModelMap modelMap, HttpServletRequest request) {
		logger.info("register GET controller invoked");
		
		Locale locale = RequestContextUtils.getLocale(request);
		modelMap.addAttribute("langOptions", MenuBuilder.getLanguageOptions(locale));
		modelMap.addAttribute("selectedLang", request.getParameter("lang"));
		
		return "errors/unexpectedException";
	}
}