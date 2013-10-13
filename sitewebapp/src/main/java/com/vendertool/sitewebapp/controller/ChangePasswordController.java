package com.vendertool.sitewebapp.controller;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.Language;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.ChangePasswordRequest;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sitewebapp.util.MenuBuilder;

@Controller
public class ChangePasswordController {
	private static final Logger logger = Logger.getLogger(ChangePasswordController.class);
	
	@RequestMapping(value="changePassword", method=RequestMethod.GET)
	public String getChangePasswordView(Model model, HttpServletRequest request){
		logger.info("getChangePasswordView GET controller invoked");
		
		Locale locale = RequestContextUtils.getLocale(request);
		
		ChangePasswordRequest changePasswordReq = new ChangePasswordRequest();
		model.addAttribute("changePasswordReq", changePasswordReq);
		model.addAttribute("languages", Language.getLanguages());
		model.addAttribute("langOptions", MenuBuilder.getLanguageOptions(locale));
		model.addAttribute("selectedLang", request.getParameter("lang"));
		
		return "changePassword/changePassword";
	}
	

	@RequestMapping(value="changePassword", method=RequestMethod.POST)
	public String validateEmail(
			ModelMap modelMap, 
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("account") Account account) {
		
		logger.info("validateEmail POST controller invoked");
		
		//
		// Validate password
		//
		ErrorResponse errorResponse = validateEmail(account);
		if (errorResponse != null) {
			modelMap.addAttribute("errorResponse", errorResponse);
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("askSecurityQuestions");
			try {
				rd.forward(req, res);
			}
			catch (Exception e) {
				e.printStackTrace();
				logger.log(Priority.ERROR, e.getMessage(), e);
			}
		}
		
		return "forgotPassword/forgotPassword";
	}
	
	//
	// Replace with real errorResponses
	//
	private ErrorResponse validateEmail(Account account) {
		if(account.getEmail() == null || account.getEmail().isEmpty()) {
			ErrorResponse resp = new ErrorResponse();
			resp.addFieldBindingError(
				Errors.REGISTRATION.EMAIL_MISSING,
				account.getClass().getName(),
				"newEmail");

			return resp;
		}
		else {
			return null;
		}
	}
	
	
	
	
	

}
