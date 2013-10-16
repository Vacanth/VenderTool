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
	private String confirmSessionId;
	private Integer confirmCode;
	private boolean isEmailValid;
	private String ipAddress;
	
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getConfirmSessionId() {
		return confirmSessionId;
	}

	public void setConfirmSessionId(String confirmSessionId) {
		this.confirmSessionId = confirmSessionId;
	}

	public Integer getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(Integer confirmCode) {
		this.confirmCode = confirmCode;
	}
}
