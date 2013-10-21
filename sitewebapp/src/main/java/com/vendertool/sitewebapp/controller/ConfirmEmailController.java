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
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.ConfirmEmailRequest;
import com.vendertool.sharedtypes.rnr.ConfirmEmailResponse;

@Controller
public class ConfirmEmailController {
	private static final Logger logger = Logger.getLogger(ConfirmEmailController.class);
	
	@RequestMapping(value="confirmemail", method=RequestMethod.GET)
	public String confirmEmail(Model model, HttpServletRequest httprequest) {
		
		ConfirmEmailRequest request = new ConfirmEmailRequest();
		
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
			model.addAttribute("type", "email");
			return "errors/confirmationFailed";
		}
		
		return "redirect:j_spring_security_logout";
	}
}
