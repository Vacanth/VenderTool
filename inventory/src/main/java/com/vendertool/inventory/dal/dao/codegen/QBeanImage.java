package com.vendertool.inventory.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanImage is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanImage {

    private Long accountId;

    private java.sql.Timestamp createdDate;

    private String hash;

    private String hostedUrl;

    private Byte imageFormat;

    private Long imageId;

    private String imageName;

    private java.sql.Timestamp lastModifiedDate;

    private Long refId;

    private Byte refType;

    private String size;

    private Byte sortOrderId;

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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHostedUrl() {
        return hostedUrl;
    }

    public void setHostedUrl(String hostedUrl) {
        this.hostedUrl = hostedUrl;
    }

    public Byte getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(Byte imageFormat) {
        this.imageFormat = imageFormat;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Byte getRefType() {
        return refType;
    }

    public void setRefType(Byte refType) {
        this.refType = refType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Byte getSortOrderId() {
        return sortOrderId;
    }

    public void setSortOrderId(Byte sortOrderId) {
        this.sortOrderId = sortOrderId;
    }

}

