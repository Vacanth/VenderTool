package com.vendertool.sharedtypes.core;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlAccessorType(XmlAccessType.FIELD)
public class ForgotPassword {
	
	public enum ForgotPasswordStatusEnum {
		ATTEMPTED(1),
		SEC_QUES_FAILED(2);
		
		private int id;
		
		private ForgotPasswordStatusEnum(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
		
		public static ForgotPasswordStatusEnum get(int id) {
			ForgotPasswordStatusEnum[] enums = ForgotPasswordStatusEnum.values();
			for(ForgotPasswordStatusEnum e : enums) {
				if(e.getId() == id) {
					return e;
				}
			}
			return null;
		}
	}
	
	private Long id;
	private String email;
	private Date createdDate;
	private Long accountId;
	private ForgotPasswordStatusEnum status;
	private String ipAddress;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public ForgotPasswordStatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(ForgotPasswordStatusEnum status) {
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@JsonIgnore
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Account=[[")
			.append("\n\tID=").append(getId())
			.append("\n\tEMAIL=").append(getEmail())
			.append("\n\tIP ADDRESS=").append(getIpAddress())
			.append("\n\tCREATED DATE=").append(getCreatedDate())
			.append("\n\tSTATUS=").append(getStatus())
			.append("\n\tACCOUNT ID=").append(getAccountId())
		.append("]]");
		
		return sb.toString();
	}
}
