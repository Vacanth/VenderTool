package com.vendertool.fps.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanFile is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanFile {

    private Long accountId;

    private java.sql.Timestamp createdDate;

    private String fileGroupId;

    private Long fileId;

    private String fileName;

    private Integer filesCountInGroup;

    private java.sql.Timestamp lastModifiedDate;

    private String refUrl;

    private Integer status;

    private Integer storageSource;

    private Integer useCase;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getFileGroupId() {
        return fileGroupId;
    }

    public void setFileGroupId(String fileGroupId) {
        this.fileGroupId = fileGroupId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFilesCountInGroup() {
        return filesCountInGroup;
    }

    public void setFilesCountInGroup(Integer filesCountInGroup) {
        this.filesCountInGroup = filesCountInGroup;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRefUrl() {
        return refUrl;
    }

    public void setRefUrl(String refUrl) {
        this.refUrl = refUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStorageSource() {
        return storageSource;
    }

    public void setStorageSource(Integer storageSource) {
        this.storageSource = storageSource;
    }

    public Integer getUseCase() {
        return useCase;
    }

    public void setUseCase(Integer useCase) {
        this.useCase = useCase;
    }

}

