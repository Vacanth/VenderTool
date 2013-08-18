package com.vendertool.sitewebapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SigninController {
	private static final Logger logger = Logger.getLogger(SigninController.class);
	
	@RequestMapping(value="signin", method=RequestMethod.GET)
	public String getSigninView(Model model){
		logger.info("signin GET controller invoked");
		
//		SigninRequest signinRequest = new SigninRequest();
//		model.addAttribute("signinRequest", signinRequest);
		return "signin";
	}
	
	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String signin(ModelMap modelMap, HttpServletRequest request) {
		logger.info("signin POST controller invoked");
		
		return "accounthub";
	}

	
	
	
	
	
//	private String handleResponseError(SigninRequest signinRequest,
//			ModelMap modelMap, SigninResponse signinResponse) {
//		logger.log(Level.INFO, signinResponse.getClass() + 
//				" has errors: \n\t" + signinResponse.getErrors());
//
//		//make sure to set the password to null
//		signinRequest.getSignin().setPassword(null);
//		
//		//preserve the username so that the user doesn't have to enter it again
////			String username = signinResponse.getUserName();
////			if((username != null) && (!username.trim().isEmpty())) {
////				signinRequest.getSignin().setUsername(username);
////			}
//		
//		modelMap.addAttribute("signinRequest", signinRequest);
//		modelMap.addAttribute("signinResponse", signinResponse);
//		return "signin";
//	}

	
	
//	private String handleHttpFailure(ModelMap modelMap, String url,
//			ClientResponse response) {
//		logger.log(Level.ERROR, "HTTP response to POST to the service URI '" + 
//				url + "' is not successful. HTTP response code is: " + response.getStatus());
//		
//		SigninResponse httpFailedresponse = new SigninResponse();
//		httpFailedresponse.addError(Errors.SIGNIN.UNABLE_TO_SIGN_IN);
//		
//		SigninRequest blankRequest = new SigninRequest();
//		
//		modelMap.addAttribute("signinRequest", blankRequest);
//		modelMap.addAttribute("signinResponse", httpFailedresponse);
//		return "signin";
//	}
	
//	private static final String WEB_SERVICE_PATH = "/vendertoolsrvs";
//	private static final String SIGNIN_PATH = "/signin";
	
}