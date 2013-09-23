package com.vendertool.registration.email;

import com.vendertool.common.email.EmailDataModel;

public class ChangeEmailDataModel extends EmailDataModel {

	private static final long serialVersionUID = 7531752441675540014L;
	
	private String previousEmail;
	private String contactusEmail;
	
	public String getPreviousEmail() {
		return previousEmail;
	}
	
	public void setPreviousEmail(String otherEmail) {
		this.previousEmail = otherEmail;
	}
	
	public String getContactusEmail() {
		return contactusEmail;
	}
	
	public void setContactusEmail(String contactusEmail) {
		this.contactusEmail = contactusEmail;
	}
}
