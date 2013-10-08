package com.vendertool.registration.email;

import com.vendertool.common.email.EmailDataModel;

public class ConfirmRegistrationEmailDataModel extends EmailDataModel{

	private static final long serialVersionUID = -6827590991408380088L;
	
	private String confirmationUrl;

	public String getConfirmationUrl() {
		return confirmationUrl;
	}

	public void setConfirmationUrl(String confirmationUrl) {
		this.confirmationUrl = confirmationUrl;
	}
}