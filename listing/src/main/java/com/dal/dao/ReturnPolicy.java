package com.dal.dao;

// Generated May 26, 2013 1:11:57 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReturnPolicy generated by hbm2java
 */
@Entity
@Table(name = "return_policy", catalog = "PRODUCTDB")
public class ReturnPolicy implements java.io.Serializable {

	private long policyId;
	private String policyName;
	private Long accountId;
	private Integer returnAddrId;
	private Integer payerId;
	private Integer returnDuration;
	private String policyDetails;
	private Byte status;
	private Date createDate;
	private Date lastModifiedDate;
	private Byte lastModifiedApp;

	public ReturnPolicy() {
	}

	public ReturnPolicy(long policyId) {
		this.policyId = policyId;
	}

	public ReturnPolicy(long policyId, String policyName, Long accountId,
			Integer returnAddrId, Integer payerId, Integer returnDuration,
			String policyDetails, Byte status, Date createDate,
			Date lastModifiedDate, Byte lastModifiedApp) {
		this.policyId = policyId;
		this.policyName = policyName;
		this.accountId = accountId;
		this.returnAddrId = returnAddrId;
		this.payerId = payerId;
		this.returnDuration = returnDuration;
		this.policyDetails = policyDetails;
		this.status = status;
		this.createDate = createDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedApp = lastModifiedApp;
	}

	@Id
	@Column(name = "POLICY_ID", unique = true, nullable = false)
	public long getPolicyId() {
		return this.policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}

	@Column(name = "POLICY_NAME", length = 45)
	public String getPolicyName() {
		return this.policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	@Column(name = "ACCOUNT_ID")
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "RETURN_ADDR_ID")
	public Integer getReturnAddrId() {
		return this.returnAddrId;
	}

	public void setReturnAddrId(Integer returnAddrId) {
		this.returnAddrId = returnAddrId;
	}

	@Column(name = "PAYER_ID")
	public Integer getPayerId() {
		return this.payerId;
	}

	public void setPayerId(Integer payerId) {
		this.payerId = payerId;
	}

	@Column(name = "RETURN_DURATION")
	public Integer getReturnDuration() {
		return this.returnDuration;
	}

	public void setReturnDuration(Integer returnDuration) {
		this.returnDuration = returnDuration;
	}

	@Column(name = "POLICY_DETAILS", length = 4000)
	public String getPolicyDetails() {
		return this.policyDetails;
	}

	public void setPolicyDetails(String policyDetails) {
		this.policyDetails = policyDetails;
	}

	@Column(name = "STATUS")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_DATE", length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Column(name = "LAST_MODIFIED_APP")
	public Byte getLastModifiedApp() {
		return this.lastModifiedApp;
	}

	public void setLastModifiedApp(Byte lastModifiedApp) {
		this.lastModifiedApp = lastModifiedApp;
	}

}