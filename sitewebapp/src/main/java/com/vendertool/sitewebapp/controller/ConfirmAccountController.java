package com.vendertool.sitewebapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vendertool.sharedapp.RestServiceClientHelper;
import com.vendertool.sharedapp.URLConstants;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationRequest;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationResponse;

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
			confirmRegRequest.setEmail(emails[0]);
			emptyRequest = false;
		}
		
		String[] sessiontokens = reqMap.get("sessiontoken");
		if(sessiontokens != null) {
			acctConf.setConfirmSessionId(sessiontokens[0]);
			emptyRequest = false;
		}
		
		String[] codes = reqMap.get("confirmationcode");
		if((codes != null) && (codes[0] != null)) {
			acctConf.setConfirmCode(Integer.parseInt(codes[0]));
			emptyRequest = false;
		}
		
		if(emptyRequest) {
			return "home";
		}
		
		String hostName = RestServiceClientHelper.getServerURL(httprequest);
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_CONFIRM_PATH;
		
		Response response = RestServiceClientHelper
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
		
		
		ConfirmRegistrationResponse confirmAccountresponse = response.readEntity(ConfirmRegistrationResponse.class);
		if(confirmAccountresponse.hasErrors()) {
			return "confirmaccountfailed";
		}
		
		return "redirect:signIn?justConfAccount=true";
	}
}
