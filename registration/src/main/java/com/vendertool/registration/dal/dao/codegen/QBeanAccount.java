package com.vendertool.registration.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanAccount is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanAccount {

    private Long accountId;

    private String alternateEmail;

    private Long billingAddrId;

    private String contactPhoneHome;

    private String contactPhoneMobile;

    private String contactPhoneWork;

    private java.sql.Timestamp createdDate;

    private String currencyCodeIso3;

    private String emailAddr;

    private String firstName;

    private String language;

    private java.sql.Timestamp lastLoginDate;

    private Integer lastModifiedApp;

    private java.sql.Timestamp lastModifiedDate;

    private String lastName;

    private String middleName;

    private String password;

    private byte[] picture;

    private Long registrationAddrId;

    private String roles;

    private String salt;

    private Integer status;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public Long getBillingAddrId() {
        return billingAddrId;
    }

    public void setBillingAddrId(Long billingAddrId) {
        this.billingAddrId = billingAddrId;
    }

    public String getContactPhoneHome() {
        return contactPhoneHome;
    }

    public void setContactPhoneHome(String contactPhoneHome) {
        this.contactPhoneHome = contactPhoneHome;
    }

    public String getContactPhoneMobile() {
        return contactPhoneMobile;
    }

    public void setContactPhoneMobile(String contactPhoneMobile) {
        this.contactPhoneMobile = contactPhoneMobile;
    }

    public String getContactPhoneWork() {
        return contactPhoneWork;
    }

    public void setContactPhoneWork(String contactPhoneWork) {
        this.contactPhoneWork = contactPhoneWork;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCurrencyCodeIso3() {
        return currencyCodeIso3;
    }

    public void setCurrencyCodeIso3(String currencyCodeIso3) {
        this.currencyCodeIso3 = currencyCodeIso3;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public java.sql.Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(java.sql.Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Long getRegistrationAddrId() {
        return registrationAddrId;
    }

    public void setRegistrationAddrId(Long registrationAddrId) {
        this.registrationAddrId = registrationAddrId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

