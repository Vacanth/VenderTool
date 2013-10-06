package com.vendertool.sharedtypes.rnr;

public class BaseRequest implements Request {

	private String signedInEmail;

	public BaseRequest(){}

	public String getSignedInEmail() {
		return signedInEmail;
	}

	public void setSignedInEmail(String signedInEmail) {
		this.signedInEmail = signedInEmail;
	}
}
