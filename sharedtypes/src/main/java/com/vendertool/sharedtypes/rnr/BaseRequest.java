package com.vendertool.sharedtypes.rnr;

public class BaseRequest implements Request {

	private String emailId;
	private Long accountId;

	public BaseRequest(){}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
