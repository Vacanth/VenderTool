package com.vendertool.sitewebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vendertool.sharedapp.RestServiceClientHelper;
import com.vendertool.sharedapp.URLConstants;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ChangeForgotPasswordRequest;
import com.vendertool.sharedtypes.rnr.ChangeForgotPasswordResponse;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sharedtypes.rnr.ProcessForgotPasswordEmailRequest;
import com.vendertool.sharedtypes.rnr.ProcessForgotPasswordEmailResponse;
import com.vendertool.sharedtypes.rnr.ValidateSecurityQuestionsRequest;
import com.vendertool.sharedtypes.rnr.ValidateSecurityQuestionsResponse;
import com.vendertool.sitewebapp.model.EmailConfirmation;
import com.vendertool.sitewebapp.model.EmailModel;
import com.vendertool.sitewebapp.model.ForgotPassword;
import com.vendertool.sitewebapp.model.SecurityQuestions;

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
		
		if(emailModel == null) {
			throw new VTRuntimeException("Null value passed to the forgot password flow");
		}
		
		ProcessForgotPasswordEmailRequest request = 
				new ProcessForgotPasswordEmailRequest();
		request.setEmail(emailModel.getEmail());
		request.setIpAddress(req.getRemoteAddr());
		
		String hostName = RestServiceClientHelper.getServerURL(req);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_PROCESS_FORGOT_PWD_EMAIL;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, request, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".validateEmail' is '" + responseCode + "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to validate email for forgot password, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		ProcessForgotPasswordEmailResponse wsresponse = 
				response.readEntity(ProcessForgotPasswordEmailResponse.class);
		if(wsresponse.hasErrors()) {
			model.addAttribute("errorResponse", new ErrorResponse(wsresponse));
			model.addAttribute("emailModel", new EmailModel());
			return "forgotPassword/forgotPassword";
		}

		model.addAttribute("emailModel", emailModel);
		model.addAttribute("isEmailValid", true);
		return "forgotPassword/forgotPassword";
	}
	
	//============================================
	//
	// Ask and process security questions
	//
	//============================================
//	@RequestMapping(value="askSecurityQuestions", method=RequestMethod.GET)
//	public String getAskQuestionsView(Model model, HttpServletRequest request){
//		logger.info("getAskQuestionsView POST controller invoked");
//		
//		String email = request.getParameter("email");
//		String confirmCode = request.getParameter("confirmationcode");
//		String confirmSessionId = request.getParameter("sessiontoken");
//		
//		EmailConfirmation emailConfirmation = new EmailConfirmation();
//		emailConfirmation.setEmail(email);
//		emailConfirmation.setConfirmCode(confirmCode);
//		emailConfirmation.setConfirmSessionId(confirmSessionId);
//		
//		// Not good
//		if (!isEmailConfirmationValid(emailConfirmation)) {
//			return "unauthorized/unauthorized";
//		}
//		else if (isTooManyAttempts(emailConfirmation)) {
//			return "accountLocked/accountLocked";
//		}
//		
//		// All good
//		SecurityQuestions securityQuestions = new SecurityQuestions();
//		securityQuestions.setEmailConfirmation(emailConfirmation);
//		List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
//		securityQuestions.setQuestions(questions);
//		
//		model.addAttribute("securityQuestions", securityQuestions);
//		return "forgotPassword/askSecurityQuestions";
//	}
	
	@RequestMapping(value="answerSecurityQuestions", method=RequestMethod.POST)
	public String answerSecurityQuestions(
			Model model,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("securityQuestions") SecurityQuestions securityQuestions) {
		logger.info("answerSecurityQuestions POST controller invoked");
		
		if((securityQuestions == null) || (securityQuestions.getEmailConfirmation() == null)) {
			throw new VTRuntimeException("Null value passed to the forgot password flow");
		}
		
		ValidateSecurityQuestionsRequest request = 
				new ValidateSecurityQuestionsRequest();
		EmailConfirmation ec = securityQuestions.getEmailConfirmation();
		request.setEmail(ec.getEmail());
		request.setConfirmCode(ec.getConfirmCode());
		request.setConfirmSessionId(ec.getConfirmSessionId());
		request.setIpAddress(req.getRemoteAddr());
		request.setQuestions(securityQuestions.getQuestions());
		
		String hostName = RestServiceClientHelper.getServerURL(req);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_VALIDATE_SEC_QUES;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, request, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".answerSecurityQuestions' is '" + responseCode + "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to validate security questions for forgot password, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		ValidateSecurityQuestionsResponse wsresponse = 
				response.readEntity(ValidateSecurityQuestionsResponse.class);
		if(wsresponse.hasError(Errors.REGISTRATION.SUSPENDED.getErrorCode()) ||
				wsresponse.hasError(Errors.REGISTRATION.TOO_MANY_ACCOUNT_PWD_ATTEMPTS.getErrorCode()) || 
				wsresponse.hasError(Errors.REGISTRATION.TOO_MANY_PWD_ATTEMPTS_IN_ONE_HOUR.getErrorCode()) || 
				wsresponse.hasError(Errors.REGISTRATION.MAX_ACCOUNT_RECONFIRM_ATTEMPTS_REACHED.getErrorCode())) {
			
			return "accountLocked/accountLocked";
		}
		
		if(wsresponse.hasErrors()) {
			model.addAttribute("errorResponse", new ErrorResponse(wsresponse));
			model.addAttribute("securityQuestions", securityQuestions);
			return "forgotPassword/askSecurityQuestions";
		}

		// Answers are good
		ForgotPassword forgotPassword = new ForgotPassword();
		forgotPassword.setEmailConfirmation(securityQuestions.getEmailConfirmation());
		
		model.addAttribute("forgotPassword", forgotPassword);
		
		return "forgotPassword/changePassword";
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

		if((forgotPassword == null) || (forgotPassword.getEmailConfirmation() == null)) {
			throw new VTRuntimeException("Null value passed to the forgot password flow");
		}
		
		ChangeForgotPasswordRequest request = new ChangeForgotPasswordRequest();
		EmailConfirmation ec = forgotPassword.getEmailConfirmation();
		
		request.setConfirmCode(ec.getConfirmCode());
		request.setEmail(ec.getEmail());
		request.setConfirmSessionId(ec.getConfirmSessionId());
		request.setNewPassword(forgotPassword.getNewPassword());
		request.setConfirmPassword(forgotPassword.getConfirmPassword());
		request.setIpAddress(req.getRemoteAddr());
		
		String hostName = RestServiceClientHelper.getServerURL(req);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_CHANGE_FORGOT_PASSWORD;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, request, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".processChangePassword' is '" + responseCode + "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to change password during the forgot password flow, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		ChangeForgotPasswordResponse wsresponse = 
				response.readEntity(ChangeForgotPasswordResponse.class);
		
		if(wsresponse.hasError(Errors.REGISTRATION.SUSPENDED.getErrorCode()) ||
				wsresponse.hasError(Errors.REGISTRATION.TOO_MANY_ACCOUNT_PWD_ATTEMPTS.getErrorCode()) || 
				wsresponse.hasError(Errors.REGISTRATION.TOO_MANY_PWD_ATTEMPTS_IN_ONE_HOUR.getErrorCode()) || 
				wsresponse.hasError(Errors.REGISTRATION.MAX_ACCOUNT_RECONFIRM_ATTEMPTS_REACHED.getErrorCode())) {
			
			return "accountLocked/accountLocked";
		}
		
		if(wsresponse.hasErrors()) {
			model.addAttribute("forgotPassword", forgotPassword);
			model.addAttribute("errorResponse", new ErrorResponse(wsresponse));
			return "forgotPassword/changePassword";
		}
		
		return "redirect:signIn";
	}
}
