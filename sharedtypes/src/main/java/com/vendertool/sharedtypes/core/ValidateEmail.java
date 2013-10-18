package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ValidateEmail {

	private String email;
	private boolean isEmailValid;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEmailValid() {
		return isEmailValid;
	}
	public void setEmailValid(boolean isEmailValid) {
		this.isEmailValid = isEmailValid;
	}
	
	
}
