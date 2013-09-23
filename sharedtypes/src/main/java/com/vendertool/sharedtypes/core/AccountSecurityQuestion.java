package com.vendertool.sharedtypes.core;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlAccessorType(XmlAccessType.FIELD)
public class AccountSecurityQuestion {
	private Long id;
	private SecurityQuestionCodeEnum questionCode;
	private String answer;
	private Date createdDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public SecurityQuestionCodeEnum getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(SecurityQuestionCodeEnum questionCode) {
		this.questionCode = questionCode;
	}

	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@JsonIgnore
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nAccountSecurityQuestion=[[")
			.append("\n\tID=").append(getId())
			.append("\n\tQUESTION CODE=").append(getQuestionCode())
			.append("\n\tANSWER=").append(getAnswer())
			.append("\n\tCREATED DATE=").append(getCreatedDate())
		.append("]]");
		
		return sb.toString();
	}
}
