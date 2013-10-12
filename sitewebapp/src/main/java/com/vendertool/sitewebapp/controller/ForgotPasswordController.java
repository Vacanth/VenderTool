package com.vendertool.sitewebapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.Language;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sitewebapp.util.MenuBuilder;

@Controller
public class ForgotPasswordController {
	private static final Logger logger = Logger.getLogger(ForgotPasswordController.class);
	
	@RequestMapping(value="forgotPassword", method=RequestMethod.GET)
	public String getForgotPasswordView(Model model, HttpServletRequest request){
		logger.info("getForgotPasswordView GET controller invoked");
		
		Locale locale = RequestContextUtils.getLocale(request);

		model.addAttribute("languages", Language.getLanguages());
		model.addAttribute("langOptions", MenuBuilder.getLanguageOptions(locale));
		model.addAttribute("selectedLang", request.getParameter("lang"));
		
		return "forgotPassword/forgotPassword";
	}
	

	@RequestMapping(value="forgotPassword", method=RequestMethod.POST)
	public String validateEmail(
			ModelMap modelMap, 
			HttpServletRequest request,
			@ModelAttribute("account") Account account) {
		
		logger.info("validateEmail POST controller invoked");
		
		System.err.println("xxxxxx email:" + account.getEmail());
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		
		modelMap.addAttribute("errorResponse", errorResponse);

		
		return "forgotPassword/forgotPassword";
	}
	
//	@RequestMapping(value = "signin", method = RequestMethod.POST)
//	public String signin(ModelMap modelMap, HttpServletRequest request) {
//		logger.info("signin POST controller invoked");
//		
//		return "accounthub";
//	}

	
	
	
	
	

}
