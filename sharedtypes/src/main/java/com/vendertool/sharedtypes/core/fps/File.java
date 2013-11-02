package com.vendertool.sharedtypes.core.fps;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class File {

	private Long fileId;
	private String name;
	private String fileGroupId;
	private Integer filesCountInGroup;
	private Long accountId;
	private String refUrl;
	private String fileName;
	private Integer storageSource;
	private FPSUsecaseEnum useCase;
	private FPSFileStatusEnum status;
	private Date createdDate;
	private Date lastModifiedDate;
		
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileGroupId() {
		return fileGroupId;
	}
	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}
	public Integer getFilesCountInGroup() {
		return filesCountInGroup;
	}
	public void setFilesCountInGroup(Integer filesCountInGroup) {
		this.filesCountInGroup = filesCountInGroup;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getRefUrl() {
		return refUrl;
	}
	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}
	public Integer getStorageSource() {
		return storageSource;
	}
	public void setStorageSource(Integer storageSource) {
		this.storageSource = storageSource;
	}
	public FPSUsecaseEnum getUseCase() {
		return useCase;
	}
	public void setUseCase(FPSUsecaseEnum useCase) {
		this.useCase = useCase;
	}
	public FPSFileStatusEnum getStatus() {
		return status;
	}
	public void setStatus(FPSFileStatusEnum status) {
		this.status = status;
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
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
