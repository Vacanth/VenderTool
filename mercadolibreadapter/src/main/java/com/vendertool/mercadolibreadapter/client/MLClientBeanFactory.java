package com.vendertool.mercadolibreadapter.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.vendertool.common.SpringApplicationContextUtils;
import com.vendertool.common.client.ClientConfig;
import com.vendertool.sharedtypes.exception.VTRuntimeException;

/**
 * Make sure to add entry @below files
 * 
 * --> mercadolibreadapter\src\main\resources\client\prod\consumer.xml -->
 * mercadolibreadapter\src\main\resources\client\qa\consumer.xml
 * 
 * The bean id should match with the
 * 'com.vendertool.mercadolibreadapter.client.MLAPIEnum'.
 * 
 * @author Girish
 * 
 */
public class MLClientBeanFactory {

	private Map<MLAPIEnum, ClientConfig> m_clientConfigMap;
	private static final Logger logger = Logger
			.getLogger(MLClientBeanFactory.class);

	private static class MLClientBeanFactoryHolder {
		private final static MLClientBeanFactory INSTANCE = new MLClientBeanFactory();
	}

	private MLClientBeanFactory() {
		m_clientConfigMap = new HashMap<MLAPIEnum, ClientConfig>();
	}

	public static MLClientBeanFactory getInstance() {
		return MLClientBeanFactoryHolder.INSTANCE;
	}

	public void init() {

		m_clientConfigMap.put(MLAPIEnum.ADD_LISTING,
				getConfig(MLAPIEnum.ADD_LISTING.getToken()));

	}

	private ClientConfig getConfig(String token) {
		// It is ok to access ctx multiple times. If we move to top level, there
		// are chances that ctx may be null.
		ApplicationContext ctx = SpringApplicationContextUtils
				.getApplicationContext();

		ClientConfig clientConfig = (ClientConfig) ctx.getBean(token);

		// Don't tolerate if config is null.
		if (clientConfig == null) {
			logger.debug("No bean found for token" + token);
			throw new VTRuntimeException("At : "
					+ MLClientBeanFactory.class.getName()
					+ "No bean found for token" + token);
		}

		return clientConfig;
	}

	public ClientConfig getConfig(MLAPIEnum apiEnum) {
		ClientConfig clientConfig = m_clientConfigMap.get(apiEnum);

		if (clientConfig == null) {
			logger.debug("No bean found for token" + apiEnum);
			throw new VTRuntimeException("At : "
					+ MLClientBeanFactory.class.getName()
					+ "No confige found for " + apiEnum);
		}

		return clientConfig;
	}
}