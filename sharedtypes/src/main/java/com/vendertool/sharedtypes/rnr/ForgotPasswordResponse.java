package com.vendertool.sharedtypes.rnr;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.AccountSecurityQuestion;

@XmlRootElement
public class ForgotPasswordResponse extends BaseResponse {

	private String email;
	private List<AccountSecurityQuestion> questions;
	private String confirmSessionId;
	private Integer confirmCode;
	
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
}