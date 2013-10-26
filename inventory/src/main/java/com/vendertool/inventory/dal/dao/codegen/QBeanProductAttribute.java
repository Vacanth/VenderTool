package com.vendertool.inventory.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanProductAttribute is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanProductAttribute {

    private String attrCharValue;

    private java.sql.Timestamp attrDateValue;

    private String attrName;

    private java.math.BigDecimal attrNumValue;

    private Integer attrValueType;

    private java.sql.Timestamp createdDate;

    private java.sql.Timestamp lastModifiedDate;

    private Long productAttributeId;

    private Long refId;

    private Integer refType;

    public String getAttrCharValue() {
        return attrCharValue;
    }

    public void setAttrCharValue(String attrCharValue) {
        this.attrCharValue = attrCharValue;
    }

    public java.sql.Timestamp getAttrDateValue() {
        return attrDateValue;
    }

    public void setAttrDateValue(java.sql.Timestamp attrDateValue) {
        this.attrDateValue = attrDateValue;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public java.math.BigDecimal getAttrNumValue() {
        return attrNumValue;
    }

    public void setAttrNumValue(java.math.BigDecimal attrNumValue) {
        this.attrNumValue = attrNumValue;
    }

    public Integer getAttrValueType() {
        return attrValueType;
    }

    public void setAttrValueType(Integer attrValueType) {
        this.attrValueType = attrValueType;
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

    public Long getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Integer getRefType() {
        return refType;
    }

    public void setRefType(Integer refType) {
        this.refType = refType;
    }

}

