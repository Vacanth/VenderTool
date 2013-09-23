package com.vendertool.inventory.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanProductVariation is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanProductVariation {

    private java.sql.Timestamp createdDate;

    private String currencyCodeIso3;

    private java.sql.Timestamp lastModifiedDate;

    private java.math.BigDecimal price;

    private Long productId;

    private Long productVariationId;

    private Integer quantity;

    private String sku;

    private String title;

    private String url;

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

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public java.math.BigDecimal getPrice() {
        return price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

