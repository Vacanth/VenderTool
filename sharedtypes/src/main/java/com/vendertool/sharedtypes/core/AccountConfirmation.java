package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AccountConfirmation {
	private String confirmSessionId;
	private Integer confirmCode;
	
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
