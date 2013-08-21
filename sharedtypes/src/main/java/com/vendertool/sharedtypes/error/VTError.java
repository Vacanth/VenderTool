package com.vendertool.sharedtypes.error;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.codehaus.jackson.annotate.JsonIgnore;


@XmlAccessorType(XmlAccessType.FIELD)
public class VTError implements Serializable {
	private VTErrorCode errorCode;
	private String errorMessage;
	private VTErrorSeverityEnum severity;
	private VTErrorDomainEnum domain;

	public VTError() {}
	
	public VTError(VTErrorCode errorCode, VTErrorDomainEnum domain) {
		this(errorCode, null, VTErrorSeverityEnum.ERROR, domain);
	}
	
	public VTError(VTErrorCode errorCode, String errorMessage, VTErrorDomainEnum domain){
		this(errorCode, errorMessage, VTErrorSeverityEnum.ERROR, domain);
	}
	
	public VTError(VTErrorCode errorCode, String errorMessage,
			VTErrorSeverityEnum severity, VTErrorDomainEnum domain) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.domain = domain;
		this.severity = severity;
	}

	public VTErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(VTErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public VTErrorSeverityEnum getSeverity() {
		return severity;
	}

	public void setSeverity(VTErrorSeverityEnum severity) {
		this.severity = severity;
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
	
	private static final long serialVersionUID = 4207311508169879885L;
}
