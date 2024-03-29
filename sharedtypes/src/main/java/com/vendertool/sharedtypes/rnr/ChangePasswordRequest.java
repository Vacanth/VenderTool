package com.vendertool.sharedtypes.rnr;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChangePasswordRequest extends BaseRequest {
	private String email; 
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private boolean forgotPasswordUsecase;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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

	public boolean isForgotPasswordUsecase() {
		return forgotPasswordUsecase;
	}

	public void setForgotPasswordUsecase(boolean forgotPasswordUsecase) {
		this.forgotPasswordUsecase = forgotPasswordUsecase;
	}
}
