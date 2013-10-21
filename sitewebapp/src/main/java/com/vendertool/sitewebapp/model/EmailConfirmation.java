package com.vendertool.sitewebapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class EmailConfirmation {

	private String email;
	private Integer confirmCode;
	private String confirmSessionId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(Integer confirmCode) {
		this.confirmCode = confirmCode;
	}
	public String getConfirmSessionId() {
		return confirmSessionId;
	}
	public void setConfirmSessionId(String confirmSessionId) {
		this.confirmSessionId = confirmSessionId;
	}
	
	
	
}
