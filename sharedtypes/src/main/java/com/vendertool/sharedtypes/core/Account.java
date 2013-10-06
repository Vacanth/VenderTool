package com.vendertool.sharedtypes.core;

import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
	@JsonIgnore
	private static final String VALUE_DELIMITER = ",";
	
	private Long id;
	private String email;
	private String alternateEmailId;
	private String password;
	private String confirmPassword;
	private String passwordSalt;
	private ContactDetails contactDetails;
	private Address billingAddress;
	private Currency currency;
	private Language language;
	private Date createDate;
	private Date lastModifiedDate;
	private Date validTillDate;
	private Image picture;
	private Set<AccountRoleEnum> roles;
	private AccountStatusEnum accountStatus;
	private AccountConfirmation accountConf;
	private List<AccountSecurityQuestion> securityQuestions;
	
	public Account(){}
	
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

	public String getAlternateEmailId() {
		return alternateEmailId;
	}

	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contact) {
		this.contactDetails = contact;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Date getValidTillDate() {
		return validTillDate;
	}

	public void setValidTillDate(Date validTillDate) {
		this.validTillDate = validTillDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public Set<AccountRoleEnum> getRoles() {
		return roles;
	}

	public void setRoles(Set<AccountRoleEnum> roles) {
		this.roles = roles;
	}

	public AccountStatusEnum getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatusEnum accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public AccountConfirmation getAccountConf() {
		return accountConf;
	}

	public void setAccountConf(AccountConfirmation accountConf) {
		this.accountConf = accountConf;
	}
	
	/**
	 * Use this with caution, do this only on the transient entity
	 */
	public void clearPassword() {
		setPassword(null);
		setConfirmPassword(null);
		setPasswordSalt(null);
	}
	
	public void clearAccountConfirmation() {
		setAccountConf(null);
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}

	@JsonIgnore
	public Set<AccountRoleEnum> denormalize(String _roles) {
		Set<AccountRoleEnum> roles = new HashSet<AccountRoleEnum>();
		if((_roles == null) || (_roles.trim().isEmpty())) {
			return roles;
		}
		
		StringTokenizer st = new StringTokenizer(_roles, VALUE_DELIMITER);
		while(st.hasMoreTokens()) {
			String s = st.nextToken();
			AccountRoleEnum role = AccountRoleEnum.get(s);
			if(role != null) {
				roles.add(role);
			}
		}
		return roles;
	}
	
	@JsonIgnore
	public String normalize(Set<AccountRoleEnum> _roles) {
		if((_roles == null) || (_roles.isEmpty())) {
			return null;
		}
		
		//since java.util.Set doesn't provide get(idx) method
		Object[] roles = _roles.toArray();
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<roles.length; i++) {
			AccountRoleEnum role = (AccountRoleEnum)roles[i];
			sb.append(role.getValue());
			if(i < (roles.length-1)) {
				sb.append(VALUE_DELIMITER);
			}
		}
		
		return sb.toString();
	}
	
	@JsonIgnore
	public void addRole(AccountRoleEnum role) {
		if(role == null) {
			return;
		}
		
		Set<AccountRoleEnum> roles = getRoles();
		if(roles == null) {
			roles = new HashSet<AccountRoleEnum>();
			setRoles(roles);
		}
		
		roles.add(role);
	}
	
	public List<AccountSecurityQuestion> getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(List<AccountSecurityQuestion> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	@JsonIgnore
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Account=[[")
			.append("\n\tID=").append(getId())
			.append("\n\tEMAIL=").append(getEmail())
			.append("\n\tALTERNATE EMAIL=").append(getAlternateEmailId())
			.append("\n\tSTATUS=").append(getAccountStatus())
			.append("\n\tBILLING ADDRESS=").append((getBillingAddress() != null) ? getBillingAddress().toString() : null)
			.append("\n\tCONTACT DETAILS=").append((getContactDetails() != null) ? getContactDetails().toString() : null)
			.append("\n\tCURRENCY=").append((getCurrency() != null) ? getCurrency().getCurrencyCode() : null)
			.append("\n\tLANGUAGE=").append((getLanguage() != null) ? getLanguage().getEnglishName() : null)
			.append("\n\tCREATED DATE=").append(getCreateDate())
			.append("\n\tLAST MOD DATE=").append(getLastModifiedDate())
			.append("\n\tROLES=").append(normalize(getRoles()))
			.append("\n\tPASSWORD=").append(getPassword())
			.append("\n\tSALT=").append(getPasswordSalt())
			.append("\n\tSECURITY QUESTIONS=").append(getSecurityQuestions())
		.append("]]");
		
		return sb.toString();
	}
}