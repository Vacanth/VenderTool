package com.vendertool.sitewebapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.vendertool.sharedtypes.core.Account;

public class CustomAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
		
		if((authentication != null) && (authentication.isAuthenticated())) {
			CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
			Account a = user.getAccount();
			if(! a.isSecurityQuestionsSetup()) {
				setDefaultTargetUrl("/profile#/questions");
			} else {
				setDefaultTargetUrl("/accounthub");
			}
			setAlwaysUseDefaultTargetUrl(false);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
