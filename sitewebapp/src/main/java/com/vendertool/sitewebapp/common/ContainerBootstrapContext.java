package com.vendertool.sitewebapp.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vendertool.sitewebapp.security.CustomUserDetails;

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
	    
	    public static String getSignedInEmail() {
	    	if((SecurityContextHolder.getContext() == null) || 
	    			(SecurityContextHolder.getContext().getAuthentication() == null)) {
	    		return null;
	    	}
	    	
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			return user.getUsername();
	    }
	    
}
