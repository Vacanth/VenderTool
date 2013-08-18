package com.vendertool.sitewebapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationRequest;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationResponse;
import com.vendertool.sitewebapp.common.URLConstants;

@Controller
public class ConfirmAccountController {
	private static final Logger logger = Logger
			.getLogger(ConfirmAccountController.class);
	static RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "confirmaccount", method = RequestMethod.GET)
	public String confirmRegistration(HttpServletRequest httprequest) {
		Map<String, String[]> reqMap = httprequest.getParameterMap();

		ConfirmRegistrationRequest confirmRegRequest = new ConfirmRegistrationRequest();
		AccountConfirmation acctConf = new AccountConfirmation();
		confirmRegRequest.setAccountConf(acctConf);

		boolean emptyRequest = true;
		String[] emails = reqMap.get("email");
		if (emails != null) {
			confirmRegRequest.setEmailId(emails[0]);
			emptyRequest = false;
		}

		String[] sessiontokens = reqMap.get("sessiontoken");
		if (sessiontokens != null) {
			acctConf.setConfirmSessionId(sessiontokens[0]);
			emptyRequest = false;
		}

		String[] codes = reqMap.get("confirmationcode");
		if ((codes != null) && (codes[0] != null)) {
			acctConf.setConfirmCode(Integer.getInteger(codes[0]));
			emptyRequest = false;
		}

		if (emptyRequest) {
			return "confirmaccount";
		}

		String hostName = httprequest.getLocalName();
		String url = hostName + URLConstants.WEB_SERVICE_PATH
				+ URLConstants.REGISTRATION_CONFIRM_PATH;

		ResponseEntity<ConfirmRegistrationResponse> responseEntity = restTemplate
				.postForEntity(url, confirmRegRequest,
						ConfirmRegistrationResponse.class);

		ConfirmRegistrationResponse confirmAccountresponse = responseEntity
				.getBody();
		if (confirmAccountresponse.hasErrors()) {
			return "confirmaccountfailed";
		}

		return "confirmaccountsuccess";
	}
}
