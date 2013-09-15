package com.vendertool.sharedtypes.core.fps;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Job {
	
	private Long jobId;
	private Long accountId;
	private String reqFileGroupId;
	private String resFileGroupId;
	private String isoCountryCode;
	private FPSJobStatusEnum status;
	private String title;
	private FPSUsecaseEnum usecase;
	private String error;
	private Long requestFileSize; 
	private Long responseFileSize;
	private Date createdDate;
	private Date lastModifiedDate;

	public Job() {
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getReqFileGroupId() {
		return reqFileGroupId;
	}

	public void setReqFileGroupId(String reqFileGroupId) {
		this.reqFileGroupId = reqFileGroupId;
	}

	public String getResFileGroupId() {
		return resFileGroupId;
	}

	public void setResFileGroupId(String resFileGroupId) {
		this.resFileGroupId = resFileGroupId;
	}

	public String getIsoCountryCode() {
		return isoCountryCode;
	}

	public void setIsoCountryCode(String isoCountryCode) {
		this.isoCountryCode = isoCountryCode;
	}

	public FPSJobStatusEnum getStatus() {
		return status;
	}

	public void setStatus(FPSJobStatusEnum status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public FPSUsecaseEnum getUsecase() {
		return usecase;
	}

	public void setUsecase(FPSUsecaseEnum usecase) {
		this.usecase = usecase;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getRequestFileSize() {
		return requestFileSize;
	}

	public void setRequestFileSize(Long requestFileSize) {
		this.requestFileSize = requestFileSize;
	}

	public Long getResponseFileSize() {
		return responseFileSize;
	}

	public void setResponseFileSize(Long responseFileSize) {
		this.responseFileSize = responseFileSize;
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
