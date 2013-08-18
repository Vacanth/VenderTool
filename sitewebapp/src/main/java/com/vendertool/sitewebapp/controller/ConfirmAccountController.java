package com.vendertool.sitewebapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationRequest;
import com.vendertool.sitewebapp.common.URLConstants;

@Controller
public class ConfirmAccountController {
	private static final Logger logger = Logger.getLogger(ConfirmAccountController.class);
	
	@RequestMapping(value="confirmaccount", method=RequestMethod.GET)
	public String confirmRegistration(HttpServletRequest httprequest) {
		Map<String, String[]> reqMap = httprequest.getParameterMap();
		
		ConfirmRegistrationRequest confirmRegRequest = new ConfirmRegistrationRequest();
		AccountConfirmation acctConf = new AccountConfirmation();
		confirmRegRequest.setAccountConf(acctConf);
		
		boolean emptyRequest = true;
		String[] emails = reqMap.get("email");
		if(emails != null) {
			confirmRegRequest.setEmailId(emails[0]);
			emptyRequest = false;
		}
		
		String[] sessiontokens = reqMap.get("sessiontoken");
		if(sessiontokens != null) {
			acctConf.setConfirmSessionId(sessiontokens[0]);
			emptyRequest = false;
		}
		
		String[] codes = reqMap.get("confirmationcode");
		if((codes != null) && (codes[0] != null)) {
			acctConf.setConfirmCode(Integer.getInteger(codes[0]));
			emptyRequest = false;
		}
		
		if(emptyRequest) {
			return "confirmaccount";
		}
		
		String hostName = httprequest.getLocalName();
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.REGISTRATION_CONFIRM_PATH;
		
		/*ClientResponse response = RestServiceClientHelper
				.invokeRestService(url, confirmRegRequest, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.POST);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".confirmRegistration' is '" + responseCode + "'.");
		
		//HTTP error code 201
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			throw new VTRuntimeException("Unable to confirm registration");
		}
		
		ConfirmRegistrationResponse confirmAccountresponse = response.getEntity(ConfirmRegistrationResponse.class);
		if(confirmAccountresponse.hasErrors()) {
			return "confirmaccountfailed";
		}*/
		
		return "confirmaccountsuccess";
	}
}
