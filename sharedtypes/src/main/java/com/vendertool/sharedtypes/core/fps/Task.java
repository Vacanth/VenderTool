package com.vendertool.sharedtypes.core.fps;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Task {
	private Long taskId;
	private Long jobId;
	private String requestGroupId;
	private Long accountId;
	private Long requestFileId;
	private Long recordId;
	private byte[] request;
	private byte[] response;
	private FPSTaskStatusEnum status;
	private String isoCountryCode;
	private Date createdDate;
	private Date lastModifiedDate;
	
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getRequestGroupId() {
		return requestGroupId;
	}
	public void setRequestGroupId(String requestGroupId) {
		this.requestGroupId = requestGroupId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getRequestFileId() {
		return requestFileId;
	}
	public void setRequestFileId(Long requestFileId) {
		this.requestFileId = requestFileId;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public byte[] getRequest() {
		return request;
	}
	public void setRequest(byte[] request) {
		this.request = request;
	}
	public byte[] getResponse() {
		return response;
	}
	public void setResponse(byte[] response) {
		this.response = response;
	}
	public FPSTaskStatusEnum getStatus() {
		return status;
	}
	public void setStatus(FPSTaskStatusEnum status) {
		this.status = status;
	}
	public String getIsoCountryCode() {
		return isoCountryCode;
	}
	public void setIsoCountryCode(String isoCountryCode) {
		this.isoCountryCode = isoCountryCode;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
