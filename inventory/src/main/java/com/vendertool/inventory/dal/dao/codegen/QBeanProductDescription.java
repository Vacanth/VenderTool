package com.vendertool.inventory.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanProductDescription is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanProductDescription {

    private Long accountId;

    private java.sql.Timestamp createdDate;

    private java.sql.Timestamp lastModifiedDate;

    private Long productDescriptionId;

    private String productDescriptionText;

    private String productDescriptionTitle;

    private Long productId;

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

    public Long getProductDescriptionId() {
        return productDescriptionId;
    }

    public void setProductDescriptionId(Long productDescriptionId) {
        this.productDescriptionId = productDescriptionId;
    }

    public String getProductDescriptionText() {
        return productDescriptionText;
    }

    public void setProductDescriptionText(String productDescriptionText) {
        this.productDescriptionText = productDescriptionText;
    }

    public String getProductDescriptionTitle() {
        return productDescriptionTitle;
    }

    public void setProductDescriptionTitle(String productDescriptionTitle) {
        this.productDescriptionTitle = productDescriptionTitle;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}

