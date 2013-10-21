package com.vendertool.sharedtypes.rnr;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChangeForgotPasswordRequest extends BaseRequest {
	private String email;
	private String newPassword;
	private String confirmPassword;
	private String confirmSessionId;
	private Integer confirmCode;
	private String ipAddress;
	
	public String getEmail() {
		return email;
	}
	public void 
	setEmail(String email) {
		this.email = email;
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
	
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
