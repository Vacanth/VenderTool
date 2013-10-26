package com.vendertool.fps.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanJob is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanJob {

    private Long accountId;

    private java.sql.Timestamp createdDate;

    private String error;

    private String isoCountryCode;

    private Long jobId;

    private java.sql.Timestamp lastModifiedDate;

    private String requestFileGroupId;

    private String responseFileGroupId;

    private Integer status;

    private String title;

    private Long totalRequestFileSize;

    private Long totalResponseFileSize;

    private Integer usecase;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public String getRequestFileGroupId() {
        return requestFileGroupId;
    }

    public void setRequestFileGroupId(String requestFileGroupId) {
        this.requestFileGroupId = requestFileGroupId;
    }

    public String getResponseFileGroupId() {
        return responseFileGroupId;
    }

    public void setResponseFileGroupId(String responseFileGroupId) {
        this.responseFileGroupId = responseFileGroupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTotalRequestFileSize() {
        return totalRequestFileSize;
    }

    public void setTotalRequestFileSize(Long totalRequestFileSize) {
        this.totalRequestFileSize = totalRequestFileSize;
    }

    public Long getTotalResponseFileSize() {
        return totalResponseFileSize;
    }

    public void setTotalResponseFileSize(Long totalResponseFileSize) {
        this.totalResponseFileSize = totalResponseFileSize;
    }

    public Integer getUsecase() {
        return usecase;
    }

    public void setUsecase(Integer usecase) {
        this.usecase = usecase;
    }

}

