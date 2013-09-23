package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityQuestion {
	private SecurityQuestionCodeEnum questionCode;
	private String questionDisplayName;
	
	public SecurityQuestionCodeEnum getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(SecurityQuestionCodeEnum questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionDisplayName() {
		return questionDisplayName;
	}
	
	public void setQuestionDisplayName(String questionDisplayName) {
		this.questionDisplayName = questionDisplayName;
	}
	
	@JsonIgnore
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nSecurityQuestion=[[")
			.append("\n\tQUESTION KEY=").append(getQuestionCode())
			.append("\n\tDISPLAY NAME=").append(getQuestionDisplayName())
		.append("]]");
		
		return sb.toString();
	}
}
