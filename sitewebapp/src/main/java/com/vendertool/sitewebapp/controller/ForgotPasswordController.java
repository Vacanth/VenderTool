package com.vendertool.sitewebapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sitewebapp.model.EmailConfirmation;
import com.vendertool.sitewebapp.model.EmailModel;
import com.vendertool.sitewebapp.model.ForgotPassword;
import com.vendertool.sitewebapp.model.SecurityQuestions;
import com.vendertool.sitewebapp.util.MockDataUtil;

@Controller
public class ForgotPasswordController {
	private static final Logger logger = Logger.getLogger(ForgotPasswordController.class);
	
	//============================================
	//
	// Enter email page
	//
	//============================================
	@RequestMapping(value="forgotPassword", method=RequestMethod.GET)
	public String getForgotPasswordView(Model model){
		logger.info("getForgotPasswordView GET controller invoked");
		
		model.addAttribute("emailModel", new EmailModel());
		
		return "forgotPassword/forgotPassword";
	}
	
	@RequestMapping(value="forgotPassword", method=RequestMethod.POST)
	public String validateEmail(
			Model model, 
			HttpServletRequest req,
			@ModelAttribute("emailModel") EmailModel emailModel) {
		
		logger.info("validateEmail POST controller invoked");
		
		ErrorResponse errorResponse = validateEmail(emailModel.getEmail());
		if (errorResponse != null) { // Error
			model.addAttribute("errorResponse", errorResponse);
		}
		
		else { // All good
			model.addAttribute("emailModel", emailModel);
			model.addAttribute("isEmailValid", true);
		}

		return "forgotPassword/forgotPassword";
	}
	
	//============================================
	//
	// Ask and process security questions
	//
	//============================================
	@RequestMapping(value="askSecurityQuestions", method=RequestMethod.GET)
	public String getAskQuestionsView(Model model, HttpServletRequest request){
		logger.info("getAskQuestionsView POST controller invoked");
		
		String email = request.getParameter("email");
		String confirmCode = request.getParameter("cc");
		String confirmSessionId = request.getParameter("csi");
		
		EmailConfirmation emailConfirmation = new EmailConfirmation();
		emailConfirmation.setEmail(email);
		emailConfirmation.setConfirmCode(confirmCode);
		emailConfirmation.setConfirmSessionId(confirmSessionId);
		
		// Not good
		if (!isEmailConfirmationValid(emailConfirmation)) {
			return "unauthorized/unauthorized";
		}
		else if (isTooManyAttempts(emailConfirmation)) {
			return "accountLocked/accountLocked";
		}
		
		// All good
		SecurityQuestions securityQuestions = new SecurityQuestions();
		securityQuestions.setEmailConfirmation(emailConfirmation);
		List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
		securityQuestions.setQuestions(questions);
		
		model.addAttribute("securityQuestions", securityQuestions);
		return "forgotPassword/askSecurityQuestions";
	}
	
	@RequestMapping(value="answerSecurityQuestions", method=RequestMethod.POST)
	public String answerSecurityQuestions(
			Model model,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("securityQuestions") SecurityQuestions securityQuestions) {
		logger.info("answerSecurityQuestions POST controller invoked");

		// Not good
		if (!isEmailConfirmationValid(securityQuestions.getEmailConfirmation())) {
			return "unauthorized/unauthorized";
		}
		else if (isTooManyAttempts(securityQuestions.getEmailConfirmation())) {
			return "accountLocked/accountLocked";
		}

		// Validate answers
		ErrorResponse errorResponse = validateAnswers(securityQuestions.getQuestions());
		if (errorResponse != null) {
			model.addAttribute("errorResponse", errorResponse);
			
			 // Echo back the questions
			List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
			securityQuestions.setQuestions(questions);
			model.addAttribute("securityQuestions", securityQuestions);

			return "forgotPassword/askSecurityQuestions";
		}
		else {
			// Answers are good
			ForgotPassword forgotPassword = new ForgotPassword();
			forgotPassword.setEmailConfirmation(securityQuestions.getEmailConfirmation());
			
			model.addAttribute("forgotPassword", forgotPassword);
			
			return "forgotPassword/changePassword";
		}
	}
	
	//============================================
	//
	// Process changed password
	//
	//============================================
	@RequestMapping(value="processChangePassword", method=RequestMethod.POST)
	public String processChangePassword(
			Model model,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("forgotPassword") ForgotPassword forgotPassword) {
		logger.info("answerSecurityQuestions POST controller invoked");

		// Not good
		if (!isEmailConfirmationValid(forgotPassword.getEmailConfirmation())) {
			return "unauthorized/unauthorized";
		}
		else if (isTooManyAttempts(forgotPassword.getEmailConfirmation())) {
			return "accountLocked/accountLocked";
		}
		
		// Validate password
		ErrorResponse errorResponse = validatePassword(forgotPassword.getNewPassword(), forgotPassword.getConfirmPassword());
		if (errorResponse != null) {
			model.addAttribute("errorResponse", errorResponse);
			model.addAttribute("forgotPasswordFlow", null);

			return "forgotPassword/changePassword";
		}
		else {
			// Passwords are good
			model.addAttribute("forgotPasswordFlow", null);
			
			return "forgotPassword/success";
		}
	}

	
	
	//
	// Replace with real validation
	//
	private boolean isEmailConfirmationValid(EmailConfirmation emailConfirmation) {
		return emailConfirmation.getEmail() != null && !emailConfirmation.getEmail().trim().isEmpty();
	}
	
	//
	// Replace with real validation
	//
	private boolean isTooManyAttempts(EmailConfirmation emailConfirmation) {
		return false;
	}
	
	//
	// Replace with real errorResponses
	//
	private ErrorResponse validatePassword(String newPassword, String confirmPassword) {
		
		if (newPassword == null || 
				newPassword.trim().isEmpty()|| 
				confirmPassword == null || 
				confirmPassword.trim().isEmpty()) {
			
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
