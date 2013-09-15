package com.vendertool.registration.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanPasswordHistory is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanPasswordHistory {

    private Long accountId;

    private java.sql.Timestamp createdDate;

    private java.sql.Timestamp lastModifiedDate;

    private String password;

    private Long passwordHistoryId;

    private String salt;

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

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPasswordHistoryId() {
        return passwordHistoryId;
    }

    public void setPasswordHistoryId(Long passwordHistoryId) {
        this.passwordHistoryId = passwordHistoryId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}

