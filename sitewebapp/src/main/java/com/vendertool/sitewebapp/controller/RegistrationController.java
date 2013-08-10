package com.vendertool.sitewebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.jersey.api.client.ClientResponse;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.RegisterAccountRequest;
import com.vendertool.sharedtypes.rnr.RegisterAccountResponse;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;

@Controller
public class RegistrationController {
	private static final Logger logger = Logger.getLogger(RegistrationController.class);
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public String getRegistrationView(Model model) {
		logger.info("register GET controller invoked");
		
		RegisterAccountResponse registerAccountresponse = new RegisterAccountResponse();
		Account account = new Account();
		registerAccountresponse.setAccount(account);
		
		model.addAttribute("registerAccountresponse", registerAccountresponse);
		return "register";
	}
	
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(ModelMap modelMap, HttpServletRequest request) {
		logger.info("register POST controller invoked");
		RegisterAccountRequest registerAccountRequest = (RegisterAccountRequest) modelMap.get("registrationRequest");
		//RegisterAccountRequest registrationRequest = new RegisterAccountRequest();
		//registrationRequest.setAccount(account);
		
		String hostName = request.getLocalName();
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.REGISTRATION_REGISTER_PATH;
		
		ClientResponse response = RestServiceClientHelper
				.invokeRestService(url, registerAccountRequest, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".register' is '" + responseCode + "'.");
		
		//HTTP error code 201
		if(response.getStatus() != Response.Status.CREATED.getStatusCode()) {
			throw new VTRuntimeException("Unable to register");
		}
		
		RegisterAccountResponse registerAccountresponse = response.getEntity(RegisterAccountResponse.class);
		if(registerAccountresponse.hasErrors()) {
			logger.error("Registration failed with errors: " + registerAccountresponse.getErrors());
			
			modelMap.addAttribute("registerAccountresponse", registerAccountresponse);
			return "register";
		}
		
		return "myhome";
	}
	
}
