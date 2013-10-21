package com.vendertool.sitewebapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vendertool.sharedapp.RestServiceClientHelper;
import com.vendertool.sharedapp.URLConstants;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ConfirmForgotPasswordEmailRequest;
import com.vendertool.sharedtypes.rnr.ConfirmForgotPasswordEmailResponse;
import com.vendertool.sitewebapp.model.EmailConfirmation;
import com.vendertool.sitewebapp.model.ForgotPassword;
import com.vendertool.sitewebapp.model.SecurityQuestions;

@Controller
public class ConfirmForgotPasswordEmailController {
	private static final Logger logger = Logger.getLogger(ConfirmEmailController.class);
	
	@RequestMapping(value="confirmForgotPasswordEmail", method=RequestMethod.GET)
	public String confirmEmail(ModelMap modelMap, HttpServletRequest httprequest) {
		
		ConfirmForgotPasswordEmailRequest request = new ConfirmForgotPasswordEmailRequest();
		
		String email = httprequest.getParameter("email");
		String sessiontoken = httprequest.getParameter("sessiontoken");
		String _code = httprequest.getParameter("confirmationcode");
		Integer code = null;
		if((_code != null) && (!_code.trim().isEmpty())) {
			code = Integer.parseInt(_code);
		}
		
		if((email == null) && (sessiontoken == null) && (code == null)) {
			return "redirect:home";
		}
		
		request.setEmail(email);
		request.setConfirmCode(code);
		request.setConfirmSessionId(sessiontoken);
		request.setIpAddress(httprequest.getRemoteAddr());
		
		String hostName = RestServiceClientHelper.getServerURL(httprequest);
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_CONFIRM_FORGOT_PWD_EMAIL;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, request, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".confirmEmail' is '" + responseCode + "'.");
		
		//HTTP error code 201
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			throw new VTRuntimeException("Unable to confirm email");
		}
		
		
		ConfirmForgotPasswordEmailResponse confirmEmailresponse = 
				response.readEntity(ConfirmForgotPasswordEmailResponse.class);
		if(confirmEmailresponse.hasErrors()) {
			return "redirect:home";
		}
		
		EmailConfirmation emailConfirmation = new EmailConfirmation();
		emailConfirmation.setEmail(request.getEmail());
		emailConfirmation.setConfirmCode(request.getConfirmCode());
		emailConfirmation.setConfirmSessionId(request.getConfirmSessionId());
		
		List<AccountSecurityQuestion> questions = confirmEmailresponse.getQuestions();
		
		if((questions == null) || (questions.isEmpty())) {
			ForgotPassword forgotPassword = new ForgotPassword();
			forgotPassword.setEmailConfirmation(emailConfirmation);
			
			modelMap.addAttribute("forgotPassword", forgotPassword);
			return "forgotPassword/changePassword";
		}
		
		SecurityQuestions securityQuestions = new SecurityQuestions();
		securityQuestions.setEmailConfirmation(emailConfirmation);
		securityQuestions.setQuestions(questions);
		
//		modelMap.put("email", request.getEmail());
//		modelMap.put("sessiontoken", request.getConfirmSessionId());
//		modelMap.put("confirmationcode", request.getConfirmCode());
		modelMap.put("securityQuestions", securityQuestions);
		return "forgotPassword/askSecurityQuestions";
	}
}
