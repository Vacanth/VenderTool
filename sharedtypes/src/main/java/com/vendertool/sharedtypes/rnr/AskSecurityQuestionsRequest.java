package com.vendertool.sharedtypes.rnr;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.AccountSecurityQuestion;

@XmlRootElement
public class AskSecurityQuestionsRequest extends BaseRequest {

	List<AccountSecurityQuestion> questions;

	public List<AccountSecurityQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<AccountSecurityQuestion> questions) {
		this.questions = questions;
	}
	
}
