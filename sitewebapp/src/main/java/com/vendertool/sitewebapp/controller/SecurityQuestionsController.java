package com.vendertool.sitewebapp.controller;

import java.util.HashMap;
import java.util.List;
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
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.core.SecurityQuestion;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sharedtypes.rnr.GetSecurityQuestionsResponse;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsRequest;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsResponse;
import com.vendertool.sitewebapp.common.ContainerBootstrapContext;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;
import com.vendertool.sitewebapp.common.VTErrorUtil;

@Controller
public class SecurityQuestionsController {
	private static final Logger logger = Logger.getLogger(SecurityQuestionsController.class);
	
	@RequestMapping(value="questions", method=RequestMethod.GET)
	public String getSecurityQuestionsView(ModelMap modelMap, HttpServletRequest request){
		logger.info("getSecurityQuestionsView GET controller invoked");
		
		String email = ContainerBootstrapContext.getSignedInEmail();
		if((email == null) || (email.trim().isEmpty())) {
			throw new VTRuntimeException("Unable to get signed in user name");
		}
		
		SecurityQuestionsController sqcontroller = new SecurityQuestionsController();
		GetSecurityQuestionsResponse secquesResponse = 
				sqcontroller.getSecurityQuestionsResponse(email, modelMap, request);
		
		modelMap.addAttribute("secquesResponse", secquesResponse);
		
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
		
		return "securityQuestions/securityQuestions";
	}
	
	@RequestMapping(value="questions/save", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateAccountSecurityQuestions(
			@RequestBody UpdateAccountSecurityQuestionsRequest updateSecQuestionsRequest, 
			HttpServletRequest request) {
		
		logger.info("updateAccountSecurityQuestions POST controller invoked");
		
		String email = ContainerBootstrapContext.getSignedInEmail();
		if((email == null) || (email.trim().isEmpty())) {
			throw new VTRuntimeException("Unable to get signed in user name");
		}
		
		updateSecQuestionsRequest.setEmail(email);
		
		return updateSecurityQuestions(request, updateSecQuestionsRequest);
	}
	
	public GetSecurityQuestionsResponse getSecurityQuestionsResponse(String signedInEmail, ModelMap modelMap, HttpServletRequest request) {
		
		String hostName = RestServiceClientHelper.getServerURL(request);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_METADATA_GET_SEC_QUESTIONS_PATH;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, null, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.GET);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".getSecurityQuestionsView() is '" + responseCode
				+ "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to fetch security questions from metadata service, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		GetSecurityQuestionsResponse secQuestionsResponse = response
				.readEntity(GetSecurityQuestionsResponse.class);
		
		List<SecurityQuestion> secQuestions = secQuestionsResponse.getSecurityQuestions();
		if((secQuestions == null) || (secQuestions.isEmpty())) {
			throw new VTRuntimeException("Security questions are not available");
		}
		
		return secQuestionsResponse;
		
	}
	
	public Map<String, Object> updateSecurityQuestions(HttpServletRequest request, 
			UpdateAccountSecurityQuestionsRequest updateSecQuestionsRequest) {
		
		String hostName = RestServiceClientHelper.getServerURL(request);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_UPDATE_SEC_QUESTIONS_PATH;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, updateSecQuestionsRequest, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".saveSecurityQuestions(" + updateSecQuestionsRequest + ")' is '" + responseCode
				+ "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to update security questions, web service HTTP response code: "
							+ response.getStatus());
			logger.debug(ex.getMessage(), ex);
			throw ex;
		}
		
		UpdateAccountSecurityQuestionsResponse updateSecQuesResponse = response
				.readEntity(UpdateAccountSecurityQuestionsResponse.class);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(updateSecQuesResponse.hasErrors()) {
			logger.error("Update security questions failed with errors: "
					+ updateSecQuesResponse.getFieldBindingErrors());

			ErrorResponse errorResponse = new ErrorResponse(updateSecQuesResponse);
			
			Locale locale = RequestContextUtils.getLocale(request);
			VTErrorUtil.updateErrorsWithLocalizedMessages(errorResponse.getVTErrors(), locale);
			
			map.put("errorResponse", errorResponse);
			map.put("updated", false);
			
			return map;
		}
		
		map.put("updated", true);
		return map;
		
	}

	/******************************************
	 * 
	 * Get partial pages for Angular
	 * 
	 ******************************************/
	@RequestMapping(value = "questions/partial/questions", method = RequestMethod.GET)
	public String getQuestionsPartial() {
		logger.info("getQuestionsPartial controller invoked");
		return "securityQuestions/partial/questions";
	}
	
	@RequestMapping(value = "questions/partial/success", method = RequestMethod.GET)
	public String getSuccessPartial() {
		logger.info("getSuccessPartial controller invoked");
		return "securityQuestions/partial/success";
	}
}