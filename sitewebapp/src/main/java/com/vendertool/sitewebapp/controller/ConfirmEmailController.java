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

import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ConfirmEmailRequest;
import com.vendertool.sharedtypes.rnr.ConfirmEmailResponse;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;

@Controller
public class ConfirmEmailController {
	private static final Logger logger = Logger.getLogger(ConfirmEmailController.class);
	
	@RequestMapping(value="confirmemail", method=RequestMethod.GET)
	public String confirmEmail(HttpServletRequest httprequest) {
		Map<String, String[]> reqMap = httprequest.getParameterMap();
		
		ConfirmEmailRequest request = new ConfirmEmailRequest();
		
		boolean emptyRequest = true;
		String[] emails = reqMap.get("email");
		if(emails != null) {
			request.setEmail(emails[0]);
			emptyRequest = false;
		}
		
		String[] oldemails = reqMap.get("oldemail");
		if(oldemails != null) {
			request.setOldEmail(oldemails[0]);
			emptyRequest = false;
		}
		
		String[] sessiontokens = reqMap.get("sessiontoken");
		if(sessiontokens != null) {
			request.setConfirmSessionId(sessiontokens[0]);
			emptyRequest = false;
		}
		
		String[] codes = reqMap.get("confirmationcode");
		if((codes != null) && (codes[0] != null)) {
			request.setConfirmCode(Integer.parseInt(codes[0]));
			emptyRequest = false;
		}
		
		if(emptyRequest) {
			return "redirect:home";
		}
		
		String hostName = RestServiceClientHelper.getServerURL(httprequest);
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_CONFIRM_EMAIL_PATH;
		
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
		
		
		ConfirmEmailResponse confirmEmailresponse = response.readEntity(ConfirmEmailResponse.class);
		if(confirmEmailresponse.hasErrors()) {
			return "confirmemailfailed";
		}
		
		return "redirect:j_spring_security_logout";
	}
}
