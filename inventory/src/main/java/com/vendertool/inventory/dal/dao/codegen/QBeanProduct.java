package com.vendertool.inventory.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanProduct is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanProduct {

    private Long accountId;

    private java.sql.Timestamp createdDate;

    private String currencyCodeIso3;

    private Byte dimensionUnit;

    private Double height;

    private Byte lastModifiedApp;

    private java.sql.Timestamp lastModifiedDate;

    private Double length;

    private java.math.BigDecimal price;

    private String productCode;

    private Byte productCodeType;

    private Long productId;

    private String productUrl;

    private Integer quantity;

    private String sku;

    private String title;

    private Double weight;

    private Byte weightUnit;

    private Double width;

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

    public String getCurrencyCodeIso3() {
        return currencyCodeIso3;
    }

    public void setCurrencyCodeIso3(String currencyCodeIso3) {
        this.currencyCodeIso3 = currencyCodeIso3;
    }

    public Byte getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(Byte dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Byte getLastModifiedApp() {
        return lastModifiedApp;
    }

    public void setLastModifiedApp(Byte lastModifiedApp) {
        this.lastModifiedApp = lastModifiedApp;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public java.math.BigDecimal getPrice() {
        return price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Byte getProductCodeType() {
        return productCodeType;
    }

    public void setProductCodeType(Byte productCodeType) {
        this.productCodeType = productCodeType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Byte getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(Byte weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

}

