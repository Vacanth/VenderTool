package com.vendertool.sharedtypes.error;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@XmlAccessorType(XmlAccessType.FIELD)
public class VTError implements Serializable {
	private VTErrorCode errorCode;
	private String message;
	private VTErrorSeverityEnum severity;
	private VTErrorDomainEnum domain;
	private Object[] arguments;
	
	public VTError() {}
	
	public VTError(VTErrorCode errorCode, VTErrorDomainEnum domain) {
		this(errorCode, null, VTErrorSeverityEnum.ERROR, domain);
	}
	
	public VTError(VTErrorCode errorCode, String errorMessage, VTErrorDomainEnum domain){
		this(errorCode, errorMessage, VTErrorSeverityEnum.ERROR, domain);
	}
	
	public VTError(VTErrorCode errorCode, String message,
			VTErrorSeverityEnum severity, VTErrorDomainEnum domain) {
		this.errorCode = errorCode;
		this.message = message;
		this.domain = domain;
		this.severity = severity;
	}

	public VTErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(VTErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public VTErrorSeverityEnum getSeverity() {
		return severity;
	}

	public void setSeverity(VTErrorSeverityEnum severity) {
		this.severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public VTErrorDomainEnum getDomain() {
		return domain;
	}

	public void setDomain(VTErrorDomainEnum domain) {
		this.domain = domain;
	}
	
	@JsonIgnore
	public String getDomainCodeKey() {
		return getDomain() + "." + getErrorCode().getErrorCode();
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
	
	@JsonIgnore
	public VTError addArguments(Object... arguments) {
		if(arguments == null) {
			return this;
		}
		
		Object[] args = getArguments();
		if(args == null) {
			setArguments(arguments);
			return this;
		}
		
		int existinglength = args.length;
		int inputlength = arguments.length;
		
		Object[] argsNew = new Object[existinglength + inputlength];
		for(int i=0; i<existinglength; i++) {
			argsNew[i] = args[i];
		}
		
		for(int i=0; i<inputlength; i++) {
			argsNew[i] = arguments[i];
		}
		
		setArguments(argsNew);
		return this;
	}

	@JsonIgnore
	public String getErrorCodeAsString() {
		return getErrorCode().getErrorCode();
	}

	private static final long serialVersionUID = 4207311508169879885L;
}
