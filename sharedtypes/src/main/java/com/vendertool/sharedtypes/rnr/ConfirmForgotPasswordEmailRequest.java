package com.vendertool.sharedtypes.rnr;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfirmForgotPasswordEmailRequest extends BaseRequest {
	private String email;
	private String confirmSessionId;
	private Integer confirmCode;
	private String ipAddress;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
