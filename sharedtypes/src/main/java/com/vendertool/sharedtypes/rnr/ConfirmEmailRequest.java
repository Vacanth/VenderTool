package com.vendertool.sharedtypes.rnr;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfirmEmailRequest extends BaseRequest {
	private String email;
	private String confirmSessionId;
	private Integer confirmCode;
	private String oldEmail;
	private boolean changeEmailUsecase;
	
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

	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	public boolean isChangeEmailUsecase() {
		return changeEmailUsecase;
	}

	public void setChangeEmailUsecase(boolean changeEmailUsecase) {
		this.changeEmailUsecase = changeEmailUsecase;
	}
}