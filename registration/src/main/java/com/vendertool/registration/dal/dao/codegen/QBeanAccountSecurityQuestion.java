package com.vendertool.registration.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanAccountSecurityQuestion is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanAccountSecurityQuestion {

    private Long accountId;

    private Long accountSecurityQuestionId;

    private java.sql.Timestamp createdDate;

    private Integer lastModifiedApp;

    private java.sql.Timestamp lastModifiedDate;

    private String securityAnswer;

    private String securityQuestionCode;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountSecurityQuestionId() {
        return accountSecurityQuestionId;
    }

    public void setAccountSecurityQuestionId(Long accountSecurityQuestionId) {
        this.accountSecurityQuestionId = accountSecurityQuestionId;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getSecurityQuestionCode() {
        return securityQuestionCode;
    }

    public void setSecurityQuestionCode(String securityQuestionCode) {
        this.securityQuestionCode = securityQuestionCode;
    }

}

