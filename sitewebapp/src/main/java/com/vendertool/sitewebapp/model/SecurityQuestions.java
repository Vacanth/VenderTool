package com.vendertool.sitewebapp.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.vendertool.sharedtypes.core.AccountSecurityQuestion;

@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityQuestions {

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
