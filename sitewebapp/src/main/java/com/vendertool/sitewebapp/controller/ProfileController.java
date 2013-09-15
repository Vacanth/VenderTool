package com.vendertool.sitewebapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ChangeEmailRequest;
import com.vendertool.sharedtypes.rnr.ChangePasswordRequest;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sharedtypes.rnr.UpdateAccountRequest;
import com.vendertool.sitewebapp.common.URLConstants;
import com.vendertool.sitewebapp.util.MenuBuilder;
import com.vendertool.sitewebapp.util.MockDataUtil;

@Controller
public class ProfileController {
	private static final Logger logger = Logger.getLogger(ProfileController.class);

	@RequestMapping(value=URLConstants.PROFILE, method=RequestMethod.GET)
	public String getProfileView(ModelMap modelMap, HttpServletRequest request) {
		logger.info("getProfileView controller invoked");

		/***
		 * TODO: Getting the real data needs to be implemented.
		 */
		Account account = MockDataUtil.getAccount();
		
		ErrorResponse errorResponse = new ErrorResponse();

		modelMap.addAttribute("account", account);
		modelMap.addAttribute("errorResponse", errorResponse);
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
	public @ResponseBody Map<String, Object> saveProfile(@RequestBody Account account, HttpServletRequest request) {
		logger.info("saveProfile controller invoked");
		
		if (account == null) {
			throw new VTRuntimeException("Cannot update account. Account is null.");
		}

		UpdateAccountRequest updateAccountReq = new UpdateAccountRequest();
		updateAccountReq.setAccount(account);
		
		/********* This needs to be implemented ***********
		Response response = RestServiceClientHelper.post(
				updateAccountReq,
				"NEED_THIS_SERVICE_PATH",
				request);
		
		UpdateAccountResponse updateAccountRes = response.readEntity(UpdateAccountResponse.class);
		
		Account responseAccount = updateAccountRes.getAccount();
		responseAccount.clearPassword();
		ErrorResponse errorResponse = new ErrorResponse();
		
		if(updateAccountRes.hasErrors()) {
			logger.error("Update account failed with errors: " + updateAccountRes.getFieldBindingErrors());

			errorResponse = new ErrorResponse(updateAccountRes);
			
			Locale locale = RequestContextUtils.getLocale(request);
			VTErrorUtil.updateErrorsWithLocalizedMessages(errorResponse.getVTErrors(), locale);
			System.out.println("**** Default charset is: " + Charset.defaultCharset());

			
			//Added for debugging purpose, need to remove this
			//addJsonOutput(responseAccount, errorResponse, modelMap);
		}
		************************************************/
		
		/***
		 * TODO: Getting the real data needs to be implemented.
		 */
		Account responseAccount = account;
		ErrorResponse errorResponse = MockDataUtil.getUpdateAccountErrors(account);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", responseAccount);
		map.put("errorResponse", errorResponse);
		
		return map;
	}
	
	@RequestMapping(value=URLConstants.PROFILE_EMAIL, method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getEmailView() {
		logger.info("getEmailView controller invoked");

		/***
		 * TODO: Getting the real data needs to be implemented.
		 */
		ChangeEmailRequest changeEmailRequest = MockDataUtil.getEmail();
		ErrorResponse errorResponse = new ErrorResponse();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("changeEmailRequest", changeEmailRequest);
		map.put("errorResponse", errorResponse);
		
		return map;
	}
	
	@RequestMapping(value=URLConstants.PROFILE_EMAIL_SAVE, method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveEmailChange(@RequestBody ChangeEmailRequest changeEmailRequest, HttpServletRequest request) {
		logger.info("saveEmailChange controller invoked");
		
		if (changeEmailRequest == null) {
			throw new VTRuntimeException("Cannot update email. changeEmailRequest is null.");
		}
		
		/***
		 * TODO: Getting the real data needs to be implemented.
		 */
		ErrorResponse errorResponse = MockDataUtil.getUpdateEmailErrors(changeEmailRequest);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("changeEmailRequest", changeEmailRequest);
		map.put("errorResponse", errorResponse);
		
		return map;
	}

	@RequestMapping(value=URLConstants.PROFILE_PASSWORD, method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getPasswordView() {
		logger.info("getPasswordView controller invoked");

		/***
		 * TODO: Getting the real data needs to be implemented.
		 */
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
		ErrorResponse errorResponse = new ErrorResponse();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("changePasswordRequest", changePasswordRequest);
		map.put("errorResponse", errorResponse);
		
		return map;
	}
	
	@RequestMapping(value=URLConstants.PROFILE_PASSWORD_SAVE, method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> savePasswordChange(@RequestBody ChangePasswordRequest changePasswordRequest, HttpServletRequest request) {
		logger.info("savePasswordChange controller invoked");
		
		if (changePasswordRequest == null) {
			throw new VTRuntimeException("Cannot update password. changePasswordRequest is null.");
		}
		
		/***
		 * TODO: Getting the real data needs to be implemented.
		 */
		ErrorResponse errorResponse = MockDataUtil.getUpdatePasswordErrors(changePasswordRequest);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("changePasswordRequest", changePasswordRequest);
		map.put("errorResponse", errorResponse);
		
		return map;
	}
	
	@RequestMapping(value="profile/questions", method=RequestMethod.GET)
	public @ResponseBody SecurityQuestionsResponse getSecurityQuestionsView(ModelMap modelMap, HttpServletRequest request){
		logger.info("getSecurityQuestionsView GET controller invoked");
		
		// Add the questions
		SecurityQuestion q1 = new SecurityQuestion();
		q1.setId(1);
		q1.setText("What is the name of your favorite pet?");
		SecurityQuestion q2 = new SecurityQuestion();
		q2.setId(2);
		q2.setText("What is the name of your high school?");
		SecurityQuestion q3 = new SecurityQuestion();
		q3.setId(3);
		q3.setText("What is your favorite color");
		SecurityQuestion q4 = new SecurityQuestion();
		q4.setId(4);
		q4.setText("What street did you grow up on");
		List<SecurityQuestion> questionList = new ArrayList<SecurityQuestion>();
		questionList.add(q1);
		questionList.add(q2);
		questionList.add(q3);
		questionList.add(q4);
		
		// Add the answers
		SecurityQuestionAnswer sqa1 = new SecurityQuestionAnswer();
		sqa1.setQuestionId(2);
		sqa1.setAnswer("Alameda high");
		SecurityQuestionAnswer sqa2 = new SecurityQuestionAnswer();
		sqa2.setQuestionId(4);
		sqa2.setAnswer("Main St.");
		List<SecurityQuestionAnswer> questionAnswers = new ArrayList<SecurityQuestionAnswer>();
		questionAnswers.add(sqa1);
		questionAnswers.add(sqa2);
		
		
		SecurityQuestionsResponse resp = new SecurityQuestionsResponse();
		resp.setQuestions(questionList);
		resp.setQuestionAnswers(questionAnswers);

		
		return resp;
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
