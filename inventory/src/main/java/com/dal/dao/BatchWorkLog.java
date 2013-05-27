package com.dal.dao;

// Generated May 26, 2013 5:25:20 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BatchWorkLog generated by hbm2java
 */
@Entity
@Table(name = "batch_work_log", catalog = "PRODUCTDB")
public class BatchWorkLog implements java.io.Serializable {

	private BatchWorkLogId id;
	private Long accountId;
	private Long recordId;
	private byte[] request;
	private byte[] response;
	private Byte status;
	private String apiAction;
	private Integer siteId;
	private Date createdDate;
	private Date lastModifiedDate;

	public BatchWorkLog() {
	}

	public BatchWorkLog(BatchWorkLogId id) {
		this.id = id;
	}

	public BatchWorkLog(BatchWorkLogId id, Long accountId, Long recordId,
			byte[] request, byte[] response, Byte status, String apiAction,
			Integer siteId, Date createdDate, Date lastModifiedDate) {
		this.id = id;
		this.accountId = accountId;
		this.recordId = recordId;
		this.request = request;
		this.response = response;
		this.status = status;
		this.apiAction = apiAction;
		this.siteId = siteId;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "batchWorkLogId", column = @Column(name = "BATCH_WORK_LOG_ID", nullable = false)),
			@AttributeOverride(name = "batchId", column = @Column(name = "BATCH_ID", nullable = false)),
			@AttributeOverride(name = "fileId", column = @Column(name = "FILE_ID", nullable = false)) })
	public BatchWorkLogId getId() {
		return this.id;
	}

	public void setId(BatchWorkLogId id) {
		this.id = id;
	}

	@Column(name = "ACCOUNT_ID")
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "RECORD_ID")
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@Column(name = "REQUEST")
	public byte[] getRequest() {
		return this.request;
	}

	public void setRequest(byte[] request) {
		this.request = request;
	}

	@Column(name = "RESPONSE")
	public byte[] getResponse() {
		return this.response;
	}

	public void setResponse(byte[] response) {
		this.response = response;
	}

	@Column(name = "STATUS")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "API_ACTION", length = 64)
	public String getApiAction() {
		return this.apiAction;
	}

	public void setApiAction(String apiAction) {
		this.apiAction = apiAction;
	}

	@Column(name = "SITE_ID")
	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_DATE", length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}