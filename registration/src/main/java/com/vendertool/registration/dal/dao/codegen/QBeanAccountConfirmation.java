package com.vendertool.registration.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanAccountConfirmation is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanAccountConfirmation {

    private Long accountConfirmationId;

    private Long accountId;

    private Integer confirmationCode;

    private java.sql.Timestamp confirmationDate;

    private java.sql.Timestamp createdDate;

    private String emailAddr;

    private java.sql.Timestamp expiryDate;

    private Integer lastModifiedApp;

    private java.sql.Timestamp lastModifiedDate;

    private Integer numberOfAttempts;

    private String sessionId;

    private Integer status;

    public Long getAccountConfirmationId() {
        return accountConfirmationId;
    }

    public void setAccountConfirmationId(Long accountConfirmationId) {
        this.accountConfirmationId = accountConfirmationId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(Integer confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public java.sql.Timestamp getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(java.sql.Timestamp confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public java.sql.Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(java.sql.Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getLastModifiedApp() {
        return lastModifiedApp;
    }

    public void setLastModifiedApp(Integer lastModifiedApp) {
        this.lastModifiedApp = lastModifiedApp;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(Integer numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

