package com.vendertool.fps.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanTask is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanTask {

    private Long accountId;

    private java.sql.Timestamp createdDate;

    private String isoCountryCode;

    private Long jobId;

    private java.sql.Timestamp lastModifiedDate;

    private Long recordId;

    private byte[] request;

    private Long requestFileId;

    private String requestGroupId;

    private byte[] response;

    private Integer status;

    private Long taskId;

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

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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

    public Long getRequestFileId() {
        return requestFileId;
    }

    public void setRequestFileId(Long requestFileId) {
        this.requestFileId = requestFileId;
    }

    public String getRequestGroupId() {
        return requestGroupId;
    }

    public void setRequestGroupId(String requestGroupId) {
        this.requestGroupId = requestGroupId;
    }

    public byte[] getResponse() {
        return response;
    }

    public void setResponse(byte[] response) {
        this.response = response;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

}

