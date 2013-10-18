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
import com.vendertool.sharedtypes.core.EmailConfirmation;
import com.vendertool.sharedtypes.core.ValidateEmail;
import com.vendertool.sharedtypes.core.ValidateNewPassword;
import com.vendertool.sharedtypes.core.ValidateSecurityQuestions;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
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

		model.addAttribute("validateEmail", new ValidateEmail());
		
		return "forgotPassword/forgotPassword";
	}
	
	@RequestMapping(value="forgotPassword", method=RequestMethod.POST)
	public String validateEmail(
			Model model, 
			HttpServletRequest req,
			@ModelAttribute("validateEmail") ValidateEmail validateEmail) {
		
		logger.info("validateEmail POST controller invoked");
		
		ErrorResponse errorResponse = validateEmail(validateEmail.getEmail());
		if (errorResponse != null) { // Error
			model.addAttribute("errorResponse", errorResponse);
		}
		
		else { // All good
			validateEmail.setEmailValid(true);
			model.addAttribute("validateEmail", validateEmail);
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
		ValidateSecurityQuestions validateSecurityQuestions = new ValidateSecurityQuestions();
		validateSecurityQuestions.setEmailConfirmation(emailConfirmation);
		List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
		validateSecurityQuestions.setQuestions(questions);

		model.addAttribute("validateSecurityQuestions", validateSecurityQuestions);
		return "forgotPassword/askSecurityQuestions";
	}
	
	@RequestMapping(value="answerSecurityQuestions", method=RequestMethod.POST)
	public String answerSecurityQuestions(
			Model model,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("validateSecurityQuestions") ValidateSecurityQuestions validateSecurityQuestions) {
		logger.info("answerSecurityQuestions POST controller invoked");

		// Not good
		if (!isEmailConfirmationValid(validateSecurityQuestions.getEmailConfirmation())) {
			return "unauthorized/unauthorized";
		}
		else if (isTooManyAttempts(validateSecurityQuestions.getEmailConfirmation())) {
			return "accountLocked/accountLocked";
		}

		// Validate answers
		ErrorResponse errorResponse = validateAnswers(validateSecurityQuestions.getQuestions());
		if (errorResponse != null) {
			model.addAttribute("errorResponse", errorResponse);
			
			 // Echo back the questions
			List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
			validateSecurityQuestions.setQuestions(questions);
			model.addAttribute("validateSecurityQuestions", validateSecurityQuestions);

			return "forgotPassword/askSecurityQuestions";
		}
		else {
			// Answers are good
			ValidateNewPassword validateNewPassword = new ValidateNewPassword();
			validateNewPassword.setEmailConfirmation(validateSecurityQuestions.getEmailConfirmation());
			
			model.addAttribute("validateNewPassword", validateNewPassword);
			
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
			@ModelAttribute("validateNewPassword") ValidateNewPassword validateNewPassword) {
		logger.info("answerSecurityQuestions POST controller invoked");

		// Not good
		if (!isEmailConfirmationValid(validateNewPassword.getEmailConfirmation())) {
			return "unauthorized/unauthorized";
		}
		else if (isTooManyAttempts(validateNewPassword.getEmailConfirmation())) {
			return "accountLocked/accountLocked";
		}
		
		// Validate password
		ErrorResponse errorResponse = validatePassword(validateNewPassword.getNewPassword(), validateNewPassword.getConfirmPassword());
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
