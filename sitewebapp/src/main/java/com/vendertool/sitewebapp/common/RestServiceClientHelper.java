package com.vendertool.sitewebapp.common;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sitewebapp.security.CleintCredentials;
import com.vendertool.sitewebapp.security.CleintCredentialsUtil;
import com.vendertool.sitewebapp.security.CustomUserDetails;

public class RestServiceClientHelper {
	
	private static final Logger logger = Logger.getLogger(RestServiceClientHelper.class);
	
	public static Response invokeRestService(
			String url,
			BaseRequest obj, 
			Map<String, String[]> queryParams, //name-value pair
			MediaType mediaType, 
			HttpMethodEnum httpMethod) {
		
		if((httpMethod == null) || (mediaType == null)) {
			logger.log(Level.ERROR, HttpMethodEnum.class.getName() + " and "
					+ MediaType.class.getName() + " is null");
			return null;
		}
		
		if(obj != null) {
			logger.log(Level.INFO, "Service Invocation: \n\t" + httpMethod
					+ ": " + url + "\n\t Payload: \n\t" + obj);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if((auth != null) && (auth.getPrincipal() != null)) {
				CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
				obj.setSignedInEmail(user.getAccount().getEmail());
			}
		}
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		CleintCredentials cd = CleintCredentialsUtil.getClientCredentials();
		client.register(new HttpBasicAuthFilter(cd.getUsername(), cd.getPassword()));
		Entity<BaseRequest> entity = null;
		WebTarget webtarget = client.target(url);
				
		try {
			switch(httpMethod) {
				case GET: 
					if(queryParams != null) {
						addQueryParams(webtarget, queryParams);
					}
					return webtarget.request(mediaType)
									.accept(mediaType)
									.get();
					
				case POST: 
					if(obj == null) {
						logger.log(Level.ERROR, "Object to " + httpMethod + " to the service URI '" + 
												url + "' is null");
						break;
					}
				
					entity = Entity.entity(obj, mediaType);
					return webtarget.request(mediaType)
							.accept(mediaType)
							.post(entity, Response.class);
					
				case PUT:
					if(obj == null) {
						logger.log(Level.ERROR, "Object to " + httpMethod + " to the service URI '" + 
												url + "' is null");
						break;
					}
					
					entity = Entity.entity(obj, mediaType);
					return webtarget.request(mediaType)
							.accept(mediaType)
							.put(entity, Response.class);
					
				case DELETE:
					if(queryParams != null) {
						addQueryParams(webtarget, queryParams);
					}
					entity = Entity.entity(obj, mediaType);
					return webtarget.request(mediaType)
							.accept(mediaType)
							.delete(Response.class);
				default:
					break;
			}
	 
			return null;
	 
		  } catch (Exception e) {
			  logger.log(Level.ERROR, e.getMessage(), e);
			return null;
		  }
	}
	
	private static void addQueryParams(WebTarget webtarget,
			Map<String, String[]> queryParams) {
		Set<String> keys = queryParams.keySet();
		for(String key : keys) {
			Object[] values = (Object[]) queryParams.get(key);
			webtarget.queryParam(key, values);
		}
		
	}

	public static String convertObjectToJson(Object obj) {
		if(obj == null) {
			return null;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonvalue = null;
		
		try {
			jsonvalue = mapper.writeValueAsString(obj);
		} catch(JsonGenerationException jgex) {
			logger.log(Level.ERROR, jgex.getMessage(), jgex);
			jsonvalue = null;
		} catch (JsonMappingException jmex) {
			logger.log(Level.ERROR, jmex.getMessage(), jmex);
			jsonvalue = null;
		} catch (IOException ioex) {
			logger.log(Level.ERROR, ioex.getMessage(), ioex);
			jsonvalue = null;
		}
		
		return jsonvalue;
	}
	
	public static String getServerURL(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		return URLConstants.HTTP + request.getServerName()
				+ URLConstants.PORT_SEPERATOR + request.getServerPort();
	}
	
	public static Response post(BaseRequest baseRequest, String servicePath, HttpServletRequest httpRequest) {
		
		String hostName = RestServiceClientHelper.getServerURL(httpRequest);
		String url = hostName + URLConstants.WEB_SERVICE_PATH + servicePath;
		
		Response response = RestServiceClientHelper
			.invokeRestService(
				url, 
				baseRequest, 
				null, 
				MediaType.APPLICATION_JSON_TYPE,
				HttpMethodEnum.POST
			);
		
		
		int responseCode = response.getStatus();
		String className = baseRequest.getClass().getName();
		
		logger.log(Level.INFO,
			"Vendertool Web service status code for URL '" + url + "' from '" + 
			className + 
			" is '" + responseCode + "'."
		);
		
		//HTTP error code 200
		if(responseCode != Response.Status.OK.getStatusCode()) {
			throw new VTRuntimeException(className + " POST ERROR: " + responseCode);
		}

		return response;		
	}
}