package com.vendertool.registration.email;

import com.vendertool.common.email.EmailDataModel;

public class ContactUsEmailDataModel extends EmailDataModel {

	private static final long serialVersionUID = -8239421900764627332L;
	
	private String contactusEmail;

	public String getContactusEmail() {
		return contactusEmail;
	}

	public void setContactusEmail(String contactusEmail) {
		this.contactusEmail = contactusEmail;
	}
}
