package com.vendertool.mercadolibreadapter.factory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.vendertool.sharedtypes.core.HttpMethodEnum;

public class MercadolibreCommunicator {

	private MercadolibreCommunicator() {
	}

	private static class MercadolibreCommunicatorHolder {
		private static final MercadolibreCommunicator INSTANCE = new MercadolibreCommunicator();
	}
	
	public static MercadolibreCommunicator getInstance() {
		return MercadolibreCommunicatorHolder.INSTANCE;
	}
	
	public Response call(MercadolibreCommunicatorVO communicatorVO){
		ClientConfig clientConfig = new ClientConfig(JacksonJsonProvider.class);
		Client client = ClientBuilder.newClient(clientConfig);
		Entity<Object> entity = null;
		
		String url = communicatorVO.getTargetURL();
		WebTarget resource = client.target(url);
		MediaType mediaType = communicatorVO.getMediaType();
		Object request =  communicatorVO.getRequestObject();

		Response response = null;
		if (communicatorVO.getMethodEnum() == HttpMethodEnum.GET) {
			response = (Response) resource.request(mediaType).accept(mediaType).get();
		} else if (communicatorVO.getMethodEnum() == HttpMethodEnum.POST) {
			if(request == null) {
				return null;
			}
			entity = Entity.entity(request, mediaType);
			response = client.target(url)
					.request(mediaType)
					.accept(mediaType)
					.post(entity, Response.class);
		} else if (communicatorVO.getMethodEnum() == HttpMethodEnum.DELETE) {
			response = (Response) resource.request(mediaType).accept(mediaType).delete(Response.class);
		} else if (communicatorVO.getMethodEnum() == HttpMethodEnum.PUT) {
			if(request == null) {
				return null;
			}
			entity = Entity.entity(request, mediaType);
			response = client.target(url)
					.request(mediaType)
					.accept(mediaType)
					.put(entity, Response.class);
		}

		return response;
	}
}