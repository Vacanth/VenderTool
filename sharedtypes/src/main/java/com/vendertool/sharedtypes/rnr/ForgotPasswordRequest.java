package com.vendertool.sharedtypes.rnr;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.AccountSecurityQuestion;

@XmlRootElement
public class ForgotPasswordRequest extends BaseRequest {

	private String email;
	private List<AccountSecurityQuestion> questions;
	private String newPassword;
	private String confirmPassword;
	private boolean isEmailValid;
	private String emailIsValidToken;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<AccountSecurityQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<AccountSecurityQuestion> questions) {
		this.questions = questions;
	}
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
	public boolean isEmailValid() {
		return isEmailValid;
	}
	public void setEmailValid(boolean isEmailValid) {
		this.isEmailValid = isEmailValid;
	}
	public String getEmailIsValidToken() {
		return emailIsValidToken;
	}
	public void setEmailIsValidToken(String emailIsValidToken) {
		this.emailIsValidToken = emailIsValidToken;
	}
	
	
	
	
}
