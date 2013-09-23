package com.vendertool.common.email;

import java.io.Serializable;

public class EmailDataModel implements Serializable {

	private static final long serialVersionUID = 1575646077726564433L;
	
	private String toEmail;
	private String toName;
	private String fromName;
	private String subject;
	private Object[] msgParams;

	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getToEmail() {
		return toEmail;
	}
	
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	
	public String getToName() {
		return toName;
	}
	
	public void setToName(String toName) {
		this.toName = toName;
	}
	
	public String getFromName() {
		return fromName;
	}
	
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Object[] getMsgParams() {
		return this.msgParams;
	}

	public void setMsgParams(Object[] msgParams) {
		this.msgParams = msgParams;
	}
	
}