package com.vendertool.sharedtypes.core;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlAccessorType(XmlAccessType.FIELD)
public class AccountConfirmation {
	private Long id;
	private String email;
	private String confirmSessionId;
	private Integer confirmCode;
	private int confirmationAttempts;
	private Date expiryDate;
	private Date confirmationDate;
	private Date createDate;
	private AccountConfirmationStatusEnum status;
	
	
	public enum AccountConfirmationStatusEnum {
		NOT_VERIFIED(1),
		VERIFIED(2);
		
		private int id;
		
		private AccountConfirmationStatusEnum(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
		
		public static AccountConfirmationStatusEnum get(int id) {
			AccountConfirmationStatusEnum[] enums = AccountConfirmationStatusEnum.values();
			for(AccountConfirmationStatusEnum e : enums) {
				if(e.getId() == id) {
					return e;
				}
			}
			return null;
		}
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public int getConfirmationAttempts() {
		return confirmationAttempts;
	}
	
	public void incrementAttempts() {
		confirmationAttempts++;
	}

	public void setConfirmationAttempts(int confirmationAttempts) {
		this.confirmationAttempts = confirmationAttempts;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AccountConfirmationStatusEnum getStatus() {
		return status;
	}

	public void setStatus(AccountConfirmationStatusEnum status) {
		this.status = status;
	}

	@JsonIgnore
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nAccountConfirmation=[[")
			.append("\n\tID=").append(getId())
			.append("\n\tEMAIL=").append(getEmail())
			.append("\n\tCONFIRM SESSION ID=").append(getConfirmSessionId())
			.append("\n\tCONFIRM CODE=").append(getConfirmCode())
			.append("\n\tCONFIRMATION ATTEMPTS=").append(getConfirmationAttempts())
			.append("\n\tCONFIRMATION DATE=").append(getConfirmationDate())
			.append("\n\tCREATION DATE=").append(getCreateDate())
			.append("\n\tEXPIRY DATE=").append(getExpiryDate())
			.append("\n\tStatus=").append(getStatus())
		.append("]]");
		return sb.toString();
	}
}
