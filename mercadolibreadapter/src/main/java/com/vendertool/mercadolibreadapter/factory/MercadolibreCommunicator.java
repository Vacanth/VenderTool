package com.vendertool.mercadolibreadapter.factory;


public class MercadolibreCommunicator {/*
	private static MercadolibreCommunicator uniqInstance;

	private MercadolibreCommunicator() {
	}

	public static synchronized MercadolibreCommunicator getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new MercadolibreCommunicator();
		}
		return uniqInstance;
	}
	
	public ClientResponse call(MercadolibreCommunicatorVO communicatorVO){
		WebResource resource = Client.create().resource(
				communicatorVO.getTargetURL());
		MediaType mediaType[] = new MediaType[1];
		mediaType[0] = communicatorVO.getMediaType();

		ClientResponse response = null;
		if (communicatorVO.getMethodEnum() == HttpMethodEnum.GET) {
			response = (ClientResponse) resource.accept(mediaType).get(
					ClientResponse.class);
		} else if (communicatorVO.getMethodEnum() == HttpMethodEnum.POST) {
			response = resource.post(ClientResponse.class,
					communicatorVO.getRequestObject());
		} else if (communicatorVO.getMethodEnum() == HttpMethodEnum.DELETE) {
			response = resource.delete(ClientResponse.class,
					communicatorVO.getRequestObject());
		} else if (communicatorVO.getMethodEnum() == HttpMethodEnum.PUT) {
			response = resource.put(ClientResponse.class,
					communicatorVO.getRequestObject());
		}

		return response;
	}
*/}