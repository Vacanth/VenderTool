package com.vendertool.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdapterUtils {

	private AdapterUtils() {

	}

	private static class AdapterUtilsHolder {
		private final static AdapterUtils INSTANCE = new AdapterUtils();
	}

	public static AdapterUtils getInstance() {
		return AdapterUtilsHolder.INSTANCE;
	}

	public ObjectMapper getObjectMapper(boolean failOnUnknownProp) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				failOnUnknownProp);
		return mapper;
	}
}