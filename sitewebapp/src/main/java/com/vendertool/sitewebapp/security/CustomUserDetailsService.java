package com.vendertool.sitewebapp.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.rnr.GetAccountResponse;
import com.vendertool.sitewebapp.common.ContainerBootstrapContext;
import com.vendertool.sitewebapp.common.URLConstants;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = Logger
			.getLogger(CustomUserDetailsService.class);
	private static final String USERNAME_KEY = "username";
	static RestTemplate restTemplate = new RestTemplate();

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		if ((username == null) || (username.trim().isEmpty())) {
			throw new UsernameNotFoundException("Username cannot be empty");
		}

		logger.log(Level.INFO, "Security's '" + getClass().getName()
				+ "'invoked for username: '" + username + "'");

		HttpServletRequest request = ContainerBootstrapContext
				.getHttpServletRequest();
		String hostName = request.getLocalName();
		String url = hostName + URLConstants.WEB_SERVICE_PATH
				+ URLConstants.REGISTRATION_GET_ACCOUNT_PATH;

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(USERNAME_KEY, username);
		ResponseEntity<GetAccountResponse> respEntity = restTemplate
				.getForEntity(url, GetAccountResponse.class, queryParams);

		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".loadUserByUsername(" + username + ")' is '" + "'.");
		GetAccountResponse getAccountresponse = respEntity.getBody();

		if (getAccountresponse.hasErrors()) {
			throw new UsernameNotFoundException(
					"Web service response has errors, unable to fetch user details: "
							+ getAccountresponse.getErrors().toString());
		}

		Account account = getAccountresponse.getAccount();
		if (account == null) {
			throw new UsernameNotFoundException("Unable to locate user");
		}

		UserDetails userDetails = new CustomUserDetails(account);

		return userDetails;
	}
}