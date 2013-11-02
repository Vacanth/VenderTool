package com.vendertool.registration.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanForgotPassword is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanForgotPassword {

    private Long accountId;

    private java.sql.Timestamp createdDate;

    private String emailAddr;

    private Long forgotPasswordId;

    private String ipAddr;

    private java.sql.Timestamp lastModifiedDate;

    private Integer status;

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

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public Long getForgotPasswordId() {
        return forgotPasswordId;
    }

    public void setForgotPasswordId(Long forgotPasswordId) {
        this.forgotPasswordId = forgotPasswordId;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

