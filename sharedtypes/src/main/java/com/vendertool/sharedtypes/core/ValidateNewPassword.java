package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ValidateNewPassword {

	private String newPassword;
	private String confirmPassword;
	private EmailConfirmation emailConfirmation;
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public EmailConfirmation getEmailConfirmation() {
		return emailConfirmation;
	}
	public void setEmailConfirmation(EmailConfirmation emailConfirmation) {
		this.emailConfirmation = emailConfirmation;
	}
	
	
	
}
