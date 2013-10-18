package com.vendertool.sitewebapp.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.ChangeEmail;
import com.vendertool.sharedtypes.core.ChangePassword;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ChangeEmailRequest;
import com.vendertool.sharedtypes.rnr.ChangeEmailResponse;
import com.vendertool.sharedtypes.rnr.ChangePasswordRequest;
import com.vendertool.sharedtypes.rnr.ChangePasswordResponse;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sharedtypes.rnr.GetAccountResponse;
import com.vendertool.sharedtypes.rnr.GetSecurityQuestionsResponse;
import com.vendertool.sharedtypes.rnr.UpdateAccountRequest;
import com.vendertool.sharedtypes.rnr.UpdateAccountResponse;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsRequest;
import com.vendertool.sitewebapp.common.ContainerBootstrapContext;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;
import com.vendertool.sitewebapp.common.VTErrorUtil;
import com.vendertool.sitewebapp.util.MenuBuilder;

@Controller
public class ProfileController {
	private static final Logger logger = Logger.getLogger(ProfileController.class);

	
	@RequestMapping(value=URLConstants.PROFILE, method=RequestMethod.GET)
	public String getProfileView(ModelMap modelMap, HttpServletRequest request) {
		logger.info("getProfileView controller invoked");
		
		String email = ContainerBootstrapContext.getSignedInEmail();
		if(email == null) {
			return "redirect:errors/unexpectedError";
		}
		
		String hostName = RestServiceClientHelper.getServerURL(request);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_GET_ACCOUNT_PATH + URLConstants.QUERY_START + 
				URLConstants.USERNAME_KEY + URLConstants.PARAM_KEY_VALUE_SEPARATOR + email;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, null, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.GET);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".getProfileView(" + email + ")' is '" + responseCode
				+ "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to fetch account details, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		GetAccountResponse accountresponse = response.readEntity(GetAccountResponse.class);
		if(accountresponse.hasErrors()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Web service response has errors, unable to fetch account details: "
							+ accountresponse.getFieldBindingErrors().toString());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		Account account = accountresponse.getAccount();

		modelMap.addAttribute("account", account);
		modelMap.addAttribute("countryOptions", MenuBuilder.getCountryOptions(request));
		
		// Add JSON for Angular
		try {
			ObjectMapper mapper = new ObjectMapper();
			String modelMapJson= mapper.writeValueAsString(modelMap);
			modelMap.put("modelMapJson", modelMapJson);
		}
		catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
			throw new VTRuntimeException("Cannot convert modelMap to json");
		}
		
		return "profile/profile";
	}

	
	@RequestMapping(value=URLConstants.PROFILE_SAVE, method=RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveProfile(@RequestBody Account account,
			HttpServletRequest request) {
		logger.info("saveProfile controller invoked");
		
		if (account == null) {
			throw new VTRuntimeException("Cannot update account. Account is null.");
		}

		String email = ContainerBootstrapContext.getSignedInEmail();
		if((email == null) || (email.trim().isEmpty())) {
			throw new VTRuntimeException("Unable to get signed in user name");
		}
		
		account.setEmail(email);
		
		UpdateAccountRequest updateAccountReq = new UpdateAccountRequest();
		updateAccountReq.setAccount(account);
		
		String hostName = RestServiceClientHelper.getServerURL(request);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_UPDATE_PROFILE_PATH;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, updateAccountReq, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".saveProfile(" + account + ")' is '" + responseCode
				+ "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to update account profile, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		UpdateAccountResponse accountresponse = response.readEntity(UpdateAccountResponse.class);
		Map<String, Object> map = new HashMap<String, Object>();
		Account acct = accountresponse.getAccount();
		acct.setPassword(null); // Clear password so it doesn't show up in form
		
		if(accountresponse.hasErrors()) {
			logger.error("Update account profile failed with errors: " + accountresponse.getFieldBindingErrors());

			ErrorResponse errorResponse = new ErrorResponse(accountresponse);
			
			Locale locale = RequestContextUtils.getLocale(request);
			VTErrorUtil.updateErrorsWithLocalizedMessages(errorResponse.getVTErrors(), locale);

			map.put("account", acct);
			map.put("errorResponse", errorResponse);
			map.put("updated", false);
			
			return map;
		}
		
		map.put("account", acct);
		map.put("updated", true);
		return map;
	}
	
	
	@RequestMapping(value=URLConstants.PROFILE_EMAIL, method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getEmailView() {
		logger.info("getEmailView controller invoked");

		String currEmail = ContainerBootstrapContext.getSignedInEmail();
		if((currEmail == null) || (currEmail.trim().isEmpty())) {
			throw new VTRuntimeException("Unable to get signed in user name");
		}
		
		ChangeEmail changeEmail = new ChangeEmail();
		changeEmail.setCurrentEmail(currEmail);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("changeEmail", changeEmail);
		return map;
	}
	
	
	@RequestMapping(value=URLConstants.PROFILE_EMAIL_SAVE, method=RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveEmailChange(
			@RequestBody ChangeEmail changeEmail,
			HttpServletRequest request) {
		logger.info("saveEmailChange controller invoked");
		
		if (changeEmail == null) {
			throw new VTRuntimeException("Cannot update email. changeEmail is null.");
		}
		
		String email = ContainerBootstrapContext.getSignedInEmail();
		if((email == null) || (email.trim().isEmpty())) {
			throw new VTRuntimeException("Unable to get signed in user name");
		}
		
		changeEmail.setCurrentEmail(email);
		ChangeEmailRequest changeEmailReq = new ChangeEmailRequest();
		changeEmailReq.setOldEmail(changeEmail.getCurrentEmail());
		changeEmailReq.setNewEmail(changeEmail.getNewEmail());
		changeEmailReq.setConfirmEmail(changeEmail.getConfirmEmail());
		changeEmailReq.setPassword(changeEmail.getPassword());
		
		String hostName = RestServiceClientHelper.getServerURL(request);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_CHANGE_EMAIL_PATH;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, changeEmailReq, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".saveEmailChange(" + changeEmailReq + ")' is '" + responseCode
				+ "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to change email, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		ChangeEmailResponse changeEmailResponse = response.readEntity(ChangeEmailResponse.class);
		Map<String, Object> map = new HashMap<String, Object>();

		if(changeEmailResponse.hasErrors()) {
			logger.error("Change email failed with errors: " + changeEmailResponse.getFieldBindingErrors());

			ErrorResponse errorResponse = new ErrorResponse(changeEmailResponse);
			
			Locale locale = RequestContextUtils.getLocale(request);
			VTErrorUtil.updateErrorsWithLocalizedMessages(errorResponse.getVTErrors(), locale);
			
			// Clear email
			changeEmail.setPassword(null);
			
			map.put("errorResponse", errorResponse);
			map.put("changeEmail", changeEmail);

			return map;
		}
		
		// Clear form
		changeEmail.setNewEmail(null);
		changeEmail.setConfirmEmail(null);
		changeEmail.setPassword(null);
		
		map.put("changeEmail", changeEmail);
		return map;
	}

	
	@RequestMapping(value=URLConstants.PROFILE_PASSWORD, method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getPasswordView() {
		logger.info("getPasswordView controller invoked");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
	
	
	@RequestMapping(value=URLConstants.PROFILE_PASSWORD_SAVE, method=RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> savePasswordChange(
			@RequestBody ChangePassword changePassword,
			HttpServletRequest request) {
		logger.info("savePasswordChange controller invoked");
		
		if (changePassword == null) {
			throw new VTRuntimeException("Cannot update password. changePassword is null.");
		}
		
		String email = ContainerBootstrapContext.getSignedInEmail();
		if((email == null) || (email.trim().isEmpty())) {
			throw new VTRuntimeException("Unable to get signed in user name");
		}

		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
		changePasswordRequest.setEmail(email); //always set this & don't accept from the UI input
		changePasswordRequest.setForgotPasswordUsecase(false);
		changePasswordRequest.setOldPassword(changePassword.getCurrentPassword());
		changePasswordRequest.setNewPassword(changePassword.getNewPassword());
		changePasswordRequest.setConfirmPassword(changePassword.getConfirmPassword());
		
		String hostName = RestServiceClientHelper.getServerURL(request);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_CHANGE_PASSWORD_PATH;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, changePasswordRequest, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".savePasswordChange(" + changePasswordRequest + ")' is '" + responseCode
				+ "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to change password, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		ChangePasswordResponse changePasswordResponse = response.readEntity(ChangePasswordResponse.class);
		Map<String, Object> map = new HashMap<String, Object>();

		if(changePasswordResponse.hasErrors()) {
			logger.error("Change email failed with errors: " + changePasswordResponse.getFieldBindingErrors());

			ErrorResponse errorResponse = new ErrorResponse(changePasswordResponse);
			
			Locale locale = RequestContextUtils.getLocale(request);
			VTErrorUtil.updateErrorsWithLocalizedMessages(errorResponse.getVTErrors(), locale);
			
			map.put("errorResponse", errorResponse);
			map.put("changePassword", null); // Clear input fields
			
			return map;
		}

		return map;
	}
	
	@RequestMapping(value="profile/questions", method=RequestMethod.GET)
	public @ResponseBody
	GetSecurityQuestionsResponse getSecurityQuestionsView(ModelMap modelMap,
			HttpServletRequest request) {
		logger.info("getSecurityQuestionsView GET controller invoked");
		
		String email = ContainerBootstrapContext.getSignedInEmail();
		if((email == null) || (email.trim().isEmpty())) {
			throw new VTRuntimeException("Unable to get signed in user name");
		}
		
		SecurityQuestionsController sqcontroller = new SecurityQuestionsController();
		return sqcontroller.getSecurityQuestionsResponse(email, modelMap, request);
	}
	
	
	@RequestMapping(value="profile/questions/save", method=RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveSecurityQuestions(
			@RequestBody UpdateAccountSecurityQuestionsRequest updateSecQuestionsRequest,
			HttpServletRequest request) {
		
		logger.info("save security questions controller invoked");
		
		if (updateSecQuestionsRequest == null) {
			throw new VTRuntimeException("Cannot update security questions. updateSecQuestionsRequest is null.");
		}
		
		String email = ContainerBootstrapContext.getSignedInEmail();
		if((email == null) || (email.trim().isEmpty())) {
			throw new VTRuntimeException("Unable to get signed in user name");
		}
		
		updateSecQuestionsRequest.setEmail(email);
		
		return new SecurityQuestionsController()
				.updateSecurityQuestions(request, updateSecQuestionsRequest);
	}

	
	
	/******************************************
	 * 
	 * Get partial pages for Angular
	 * 
	 ******************************************/
	@RequestMapping(value = "profile/partial/account", method = RequestMethod.GET)
	public String getAccountPartial() {
		logger.info("getAccountPartial controller invoked");
		return "profile/partial/account";
	}
	
	@RequestMapping(value = "profile/partial/email", method = RequestMethod.GET)
	public String getEmailPartial() {
		logger.info("getEmailPartial controller invoked");
		return "profile/partial/email";
	}

	@RequestMapping(value = "profile/partial/password", method = RequestMethod.GET)
	public String getPasswordPartial() {
		logger.info("getPasswordPartial controller invoked");
		return "profile/partial/password";
	}
	
	@RequestMapping(value = "profile/partial/questions", method = RequestMethod.GET)
	public String getQuestionsPartial() {
		logger.info("getQuestionsPartial controller invoked");
		return "profile/partial/questions";
	}
}
