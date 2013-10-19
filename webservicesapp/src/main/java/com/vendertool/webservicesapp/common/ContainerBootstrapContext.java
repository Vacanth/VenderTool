package com.vendertool.webservicesapp.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vendertool.listing.processor.Module;

public class ContainerBootstrapContext {
	   
		private static final String ENV_PROPERTY = "vendertool.evironment";
	    private static final ThreadLocal<ContainerBootstrapContext> CTX = 
	    		new ThreadLocal<ContainerBootstrapContext>();
	    private ServletContext servletContext;
	    private EnvironmentEnum environment = EnvironmentEnum.DEVELOPMENT;

		private ContainerBootstrapContext(ServletContext servletContext) {
			this.servletContext = servletContext;
			
	    	initialize();
	    }
		
	    protected static ContainerBootstrapContext createContext(ServletContext servletContext) {
	    	ContainerBootstrapContext ctx = new ContainerBootstrapContext(servletContext);
            CTX.set(ctx);
            return ctx;
	    }
	    
	    protected void clearContext() {
	        CTX.remove();
	    }
	    
	    private void initialize() {
	    	try {
		    	String prop = System.getProperty(ENV_PROPERTY) ;
		    	if(prop != null) {
		    		environment = EnvironmentEnum.get(prop.toUpperCase());
		    	}
		    	//DO DAL Init
//		    	AccountDAOFactory.getInstance().init();
//		    	AccountConfirmationDAOFactory.getInstance().init();
//		    	AddressDAOFactory.getInstance().init();
		    	//Do processor Init
		    	Module.getInstance().init();
		    	com.vendertool.inventory.processor.Module.getInstance().init();
		    	com.vendertool.mercadolibreadapter.Module.getInstance().init();
	    	} finally {
	    		CTX.set(this);
	    	}
	    }
	    
	    
	    
	    //********** All Public methods
	    
	    public EnvironmentEnum getEnvironment() {
	    	if(environment == null) {
	    		environment = EnvironmentEnum.DEVELOPMENT;
	    	}
			return environment;
		}
	    
	    public static ContainerBootstrapContext getContext() {
	    	ContainerBootstrapContext ctx = CTX.get();
	    	if(ctx == null) {
	    		ctx = new ContainerBootstrapContext(null);
	    	}
	    	return ctx;
	    }
	    
	    public static HttpServletRequest getHttpServletRequest() {
	    	return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	    }
	    
	    public ServletContext getServletContext() {
	    	return this.servletContext;
	    }
}
