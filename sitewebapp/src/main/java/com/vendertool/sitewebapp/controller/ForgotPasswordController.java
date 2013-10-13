package com.vendertool.sitewebapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sharedtypes.rnr.ForgotPasswordRequest;
import com.vendertool.sitewebapp.util.MockDataUtil;

@Controller
public class ForgotPasswordController {
	private static final Logger logger = Logger.getLogger(ForgotPasswordController.class);
	
	@RequestMapping(value="forgotPassword", method=RequestMethod.GET)
	public String getForgotPasswordView(Model model){
		logger.info("getForgotPasswordView GET controller invoked");

		model.addAttribute("forgotPasswordReq", new ForgotPasswordRequest());
		
		return "forgotPassword/forgotPassword";
	}
	

	@RequestMapping(value="forgotPassword", method=RequestMethod.POST)
	public String validateEmail(
			Model model, 
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("forgotPasswordReq") ForgotPasswordRequest forgotPasswordReq) {
		
		logger.info("validateEmail POST controller invoked");
		
		//
		// Email not valid error
		//
		ErrorResponse errorResponse = validateEmail(forgotPasswordReq.getEmail());
		if (errorResponse != null) {
			model.addAttribute("errorResponse", errorResponse);
		}
		else {
			forgotPasswordReq.setEmailValid(true);
			model.addAttribute("forgotPasswordReq", forgotPasswordReq);
		}

		return "forgotPassword/forgotPassword";
	}
	
	@RequestMapping(value="askSecurityQuestions", method=RequestMethod.GET)
	public String getAskQuestionsView(Model model, HttpServletRequest request){
		logger.info("getAskQuestionsView POST controller invoked");

		String emailIsValidToken = request.getParameter("t");
		if (emailIsValidToken != null && !emailIsValidToken.trim().isEmpty()) {
			
			ForgotPasswordRequest forgotPasswordReq = new ForgotPasswordRequest();
			forgotPasswordReq.setEmailIsValidToken(emailIsValidToken);
			List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
			forgotPasswordReq.setQuestions(questions);

			model.addAttribute("forgotPasswordReq", forgotPasswordReq);
			return "forgotPassword/askSecurityQuestions";
		}
		else {
			return "unauthorized/unauthorized";
		}
	}
	
	@RequestMapping(value="answerSecurityQuestions", method=RequestMethod.POST)
	public String answerSecurityQuestions(
			Model model,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("forgotPasswordReq") ForgotPasswordRequest forgotPasswordReq) {
		logger.info("answerSecurityQuestions POST controller invoked");
		
		String emailIsValidToken = forgotPasswordReq.getEmailIsValidToken();
		if (emailIsValidToken != null && !emailIsValidToken.trim().isEmpty()) {
			
			//
			// Validate answers
			//
			ErrorResponse errorResponse = validateAnswers(forgotPasswordReq.getQuestions());
			if (errorResponse != null) {
				// Error case
				model.addAttribute("errorResponse", errorResponse);
				
				 // Echo back the questions
				List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
				forgotPasswordReq.setQuestions(questions);
				model.addAttribute("forgotPasswordReq", forgotPasswordReq);

				return "forgotPassword/askSecurityQuestions";
			}
			else {
				// Answers are good
				model.addAttribute("forgotPasswordReq", forgotPasswordReq);
				
				return "forgotPassword/changePassword";
			}
		}
		else {
			return "unauthorized/unauthorized";
		}
	}
	
	@RequestMapping(value="processChangePassword", method=RequestMethod.POST)
	public String processChangePassword(
			Model model,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("forgotPasswordReq") ForgotPasswordRequest forgotPasswordReq) {
		logger.info("answerSecurityQuestions POST controller invoked");
		
		String emailIsValidToken = forgotPasswordReq.getEmailIsValidToken();
		if (emailIsValidToken != null && !emailIsValidToken.trim().isEmpty()) {
			
			//
			// Validate password
			//
			ErrorResponse errorResponse = validatePassword(forgotPasswordReq);
			if (errorResponse != null) {
				// Error case
				model.addAttribute("errorResponse", errorResponse);
				model.addAttribute("forgotPasswordReq", forgotPasswordReq);

				return "forgotPassword/changePassword";
			}
			else {
				// Passwords are good
				model.addAttribute("forgotPasswordReq", forgotPasswordReq);
				
				return "forgotPassword/success";
			}
		}
		else {
			return "unauthorized/unauthorized";
		}
	}
	
	//
	// Replace with real errorResponses
	//
	private ErrorResponse validatePassword(ForgotPasswordRequest forgotPasswordReq) {
		
		if (forgotPasswordReq.getNewPassword() == null || 
			forgotPasswordReq.getNewPassword().trim().isEmpty()|| 
			forgotPasswordReq.getConfirmPassword() == null || 
			forgotPasswordReq.getConfirmPassword().trim().isEmpty()) {
			
			ErrorResponse resp = new ErrorResponse();
			resp.addFieldBindingError(
				Errors.REGISTRATION.PASSWORD_LENGTH_INCORRECT,
				"",
				"");

			return resp;
		}

		return null;
	}
	
	//
	// Replace with real errorResponses
	//
	private ErrorResponse validateAnswers(List<AccountSecurityQuestion> questions) {
		
		if (questions != null) {
			for (AccountSecurityQuestion q : questions) {
				if (q.getAnswer() == null || q.getAnswer().trim().length() == 0) {
					ErrorResponse resp = new ErrorResponse();
					resp.addFieldBindingError(
						Errors.REGISTRATION.MISSING_SECURITY_ANSWER,
						q.getClass().getName(),
						"newEmail");

					return resp;
				}
			}
		}
		
		
		return null;
	}

	
	//
	// Replace with real errorResponses
	//
	private ErrorResponse validateEmail(String email) {
		if(email == null || email.trim().isEmpty()) {
			ErrorResponse resp = new ErrorResponse();
			resp.addFieldBindingError(Errors.REGISTRATION.EMAIL_MISSING, "", "");
			return resp;
		}
		else {
			return null;
		}
	}
	
	
	
	
	

}
