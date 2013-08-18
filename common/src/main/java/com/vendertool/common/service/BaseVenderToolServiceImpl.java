package com.vendertool.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class BaseVenderToolServiceImpl implements IVenderToolService {

	public static final String VERSION_RESOURCE = "../parent/VenderToolVersion.properties";
	
	
	@RequestMapping(value="/register", method = RequestMethod.GET, produces = {"application/json"})

	public String getVersion() {
		String path = "../parent/VenderToolVersion.properties";
		InputStream stream = getClass().getResourceAsStream(path);
		if (stream == null) {
			return "UNKNOWN";
		}
		
	    Properties props = new Properties();
	    try {
	    	props.load(stream);
	 	    stream.close();
	 	    return (String)props.get("vendertool.version");
	     } catch (IOException e) {
	 	    return "UNKNOWN";
	 	 }
	}
}
