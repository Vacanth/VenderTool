package com.vendertool.sitewebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String confirmRegistration(Model model, HttpServletRequest httprequest) {
		
		ConfirmRegistrationRequest confirmRegRequest = new ConfirmRegistrationRequest();
		AccountConfirmation acctConf = new AccountConfirmation();
		confirmRegRequest.setAccountConf(acctConf);
		
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
		
		confirmRegRequest.setEmail(email);
		acctConf.setEmail(email);
		acctConf.setConfirmCode(code);
		acctConf.setConfirmSessionId(sessiontoken);
		
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
			model.addAttribute("type", "account");
			return "errors/confirmationFailed";
		}
		
		return "redirect:signIn?justConfAccount=true";
	}
}
