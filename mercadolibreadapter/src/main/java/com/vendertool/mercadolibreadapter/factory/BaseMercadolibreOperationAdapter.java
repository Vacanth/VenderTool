package com.vendertool.mercadolibreadapter.factory;

import com.vendertool.common.client.ClientConfig;
import com.vendertool.mercadolibreadapter.client.MLAPIEnum;
import com.vendertool.mercadolibreadapter.client.MLClientBeanFactory;

public abstract class BaseMercadolibreOperationAdapter implements
		IBaseMercadolibreOperationAdapter {

	private MLAPIEnum apiENUM;
	private static String ACCESS_TOKEN = "#ACCESS_TOKEN#";

	protected BaseMercadolibreOperationAdapter(MLAPIEnum apiENUM) {
		this.apiENUM = apiENUM;
	}

	public String getEndPointURL(String token) {
		ClientConfig clientConfig = MLClientBeanFactory.getInstance()
				.getConfig(apiENUM);
		String url = clientConfig.getEndPointURL();
		return token == null ? url : url.replace(ACCESS_TOKEN, token);
	}
}