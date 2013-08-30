package com.vendertool.registration.dal.passwordHistory;

// Generated Aug 27, 2013 7:19:47 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PassswordHistory generated by hbm2java
 */
@Entity
@Table(name = "passsword_history", catalog = "invdb")
public class PasswordHistory implements java.io.Serializable {

	private long passswordHistoryId;
	private Long accountId;
	private String password;
	private String salat;
	private Date createdDate;
	private Date lastModifiedDate;

	public PasswordHistory() {
	}

	public PasswordHistory(long passswordHistoryId) {
		this.passswordHistoryId = passswordHistoryId;
	}

	public PasswordHistory(long passswordHistoryId, Long accountId,
			String password, String salat, Date createdDate,
			Date lastModifiedDate) {
		this.passswordHistoryId = passswordHistoryId;
		this.accountId = accountId;
		this.password = password;
		this.salat = salat;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	@Id
	@Column(name = "passsword_history_id", unique = true, nullable = false)
	public long getPassswordHistoryId() {
		return this.passswordHistoryId;
	}

	public void setPassswordHistoryId(long passswordHistoryId) {
		this.passswordHistoryId = passswordHistoryId;
	}

	@Column(name = "account_id")
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "password", length = 512)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "salat", length = 128)
	public String getSalat() {
		return this.salat;
	}

	public void setSalat(String salat) {
		this.salat = salat;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_date", length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}