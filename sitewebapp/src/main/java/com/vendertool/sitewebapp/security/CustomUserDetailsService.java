package com.vendertool.sitewebapp.security;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.rnr.GetAccountPasswordResponse;
import com.vendertool.sitewebapp.common.ContainerBootstrapContext;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;

@Service("customUserDetailsService")
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	private static final String USERNAME_KEY = "username";
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		if((username == null) || (username.trim().isEmpty())) {
			UsernameNotFoundException ex =  new UsernameNotFoundException("Username cannot be empty");
			logger.log(Level.DEBUG, "Username cannot be empty", ex);
			throw ex;
		}
		
		logger.log(Level.INFO, "Security's '" + getClass().getName() + "'invoked for username: '" + username + "'");
		
		HttpServletRequest request = ContainerBootstrapContext.getHttpServletRequest();
		String hostName = RestServiceClientHelper.getServerURL(request);
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_REGISTRATION_GET_ACCOUNT_PWD_PATH + URLConstants.QUERY_START + 
				USERNAME_KEY + URLConstants.PARAM_KEY_VALUE_SEPARATOR + username;
		
//		Map<String, String[]> queryParams = new HashMap<String, String[]>();
//		queryParams.put(USERNAME_KEY, new String[]{username});
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, null, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.GET);
		
		int responseCode = response.getStatus();
		logger.log(Level.INFO, "Vendertool Web service status code for URL '"
				+ url + "' from '" + getClass().getName()
				+ ".loadUserByUsername(" + username + ")' is '" + responseCode
				+ "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			UsernameNotFoundException ex = new UsernameNotFoundException("Unable to fetch user details, web service HTTP response code not okay.");
			logger.log(Level.DEBUG, "Unable to fetch user details, web service HTTP response code not okay.", ex);
			throw ex;
		}
		
		GetAccountPasswordResponse accountresponse = response.readEntity(GetAccountPasswordResponse.class);
		if(accountresponse.hasErrors()) {
			throw new UsernameNotFoundException(
					"Web service response has errors, unable to fetch user details: "
							+ accountresponse.getFieldBindingErrors().toString());
		}
		
		Account account = accountresponse.getAccount();
		if(account == null) {
			UsernameNotFoundException ex = new UsernameNotFoundException("Unable to locate user");
			logger.log(Level.DEBUG, "Unable to locate user", ex);
			throw ex;
		}
		
		UserDetails userDetails = new CustomUserDetails(account);
		
		return userDetails;
	}
}