package com.vendertool.sharedtypes.rnr;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.AccountConfirmation;

@XmlRootElement
public class ConfirmRegistrationRequest extends BaseRequest {
	private String email;
	private AccountConfirmation accountConf;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AccountConfirmation getAccountConf() {
		return accountConf;
	}
	public void setAccountConf(AccountConfirmation accountConf) {
		this.accountConf = accountConf;
	}
	
}
