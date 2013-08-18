package com.vendertool.sitewebapp.common;

import javax.servlet.http.HttpServletRequest;


public class RestServiceClientHelper {/*
	
	private static final Logger logger = Logger.getLogger(RestServiceClientHelper.class);
	
	public static ClientResponse invokeRestService(
			String url,
			Object obj, 
			MultivaluedMap<String, String> queryParams, //name-value pair
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
		}
		
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			
			if((queryParams != null) && (!queryParams.isEmpty())){
				webResource.queryParams(queryParams);
			}
			
			ClientResponse response = null;
			
			switch(httpMethod) {
				case GET: 
					response = webResource
									.accept(mediaType)
									.type(mediaType)
									.get(ClientResponse.class);
					break;
				case POST: 
					if(obj == null) {
						logger.log(Level.ERROR, "Object to " + httpMethod + " to the service URI '" + 
												url + "' is null");
					}
					
					response = webResource
									.accept(mediaType)
									.type(mediaType)
									.post(ClientResponse.class, obj);
					
					break;
				case PUT:
					if(obj == null) {
						logger.log(Level.ERROR, "Object to " + httpMethod + " to the service URI '" + 
												url + "' is null");
					}
					
					response = webResource
									.accept(mediaType)
									.type(mediaType)
									.put(ClientResponse.class, obj);
					break;
				case DELETE:
					if(obj == null) {
						logger.log(Level.ERROR, "Object to " + httpMethod + " to the service URI '" + 
												url + "' is null");
					}
					
					response = webResource
									.accept(mediaType)
									.type(mediaType)
									.delete(ClientResponse.class, obj);
					break;
				default:
					break;
			}
	 
			return response;
	 
		  } catch (Exception e) {
			  logger.log(Level.ERROR, e.getMessage(), e);
			return null;
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
	
	
*/
	public static String getServerURL(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		return URLConstants.HTTP + request.getServerName()
				+ URLConstants.PORT_SEPERATOR + request.getServerPort();
	}	
}