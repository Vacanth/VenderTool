package com.vendertool.sitewebapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sharedtypes.rnr.RegisterAccountRequest;
import com.vendertool.sharedtypes.rnr.RegisterAccountResponse;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;

@Controller
public class RegisterController {
	private static final Logger logger = Logger
			.getLogger(RegisterController.class);

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String getRegistrationView(ModelMap modelMap) {
		logger.info("register GET controller invoked");

		Account account = new Account();
		ErrorResponse errorResponse = new ErrorResponse();

		modelMap.addAttribute("account", account);
		modelMap.addAttribute("errorResponse", errorResponse);
		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(ModelMap modelMap, HttpServletRequest request,
			@ModelAttribute("account") Account account) {
		logger.info("register POST controller invoked");
		// Account account = (Account) modelMap.get("account");
		RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();
		registerAccountRequest.setAccount(account);

		String hostName = RestServiceClientHelper.getServerURL(request);

		String url = hostName + URLConstants.WEB_SERVICE_PATH
				+ URLConstants.REGISTRATION_REGISTER_PATH;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RegisterAccountResponse> responseEntity = restTemplate
				.postForEntity(url, registerAccountRequest,
						RegisterAccountResponse.class);
		RegisterAccountResponse registerAccountresponse = responseEntity
				.getBody();
		if (registerAccountresponse.hasErrors()) {
			logger.error("Registration failed with errors: "
					+ registerAccountresponse.getErrors());

			Account responseAccount = registerAccountresponse.getAccount();
			ErrorResponse errorResponse = new ErrorResponse(
					registerAccountresponse);
			modelMap.addAttribute("account", responseAccount);
			modelMap.addAttribute("errorResponse", errorResponse);

			return "register";
		}

		return "accounthub";
	}

}