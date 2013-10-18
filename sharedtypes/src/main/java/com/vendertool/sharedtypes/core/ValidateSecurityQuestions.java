package com.vendertool.sharedtypes.core;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ValidateSecurityQuestions {

	private List<AccountSecurityQuestion> questions;
	private EmailConfirmation emailConfirmation;
	
	public List<AccountSecurityQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<AccountSecurityQuestion> questions) {
		this.questions = questions;
	}
	public EmailConfirmation getEmailConfirmation() {
		return emailConfirmation;
	}
	public void setEmailConfirmation(EmailConfirmation emailConfirmation) {
		this.emailConfirmation = emailConfirmation;
	}
	
	
	
	
}
