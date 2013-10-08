package com.vendertool.sharedtypes.rnr;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.AccountSecurityQuestion;

@XmlRootElement
public class UpdateAccountSecurityQuestionsRequest extends BaseRequest {
	private String email;
	List<AccountSecurityQuestion> questions;
	
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
}
