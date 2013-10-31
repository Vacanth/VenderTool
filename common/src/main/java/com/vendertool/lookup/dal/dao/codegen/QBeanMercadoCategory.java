package com.vendertool.lookup.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanMercadoCategory is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanMercadoCategory {

    private byte[] attributes;

    private String categoryId;

    private java.sql.Timestamp createdDate;

    private Integer groupId;

    private java.sql.Timestamp lastModifiedDate;

    private Long mercadoCategoryId;

    private String parentCategoryId;

    private Integer status;

    public byte[] getAttributes() {
        return attributes;
    }

    public void setAttributes(byte[] attributes) {
        this.attributes = attributes;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getMercadoCategoryId() {
        return mercadoCategoryId;
    }

    public void setMercadoCategoryId(Long mercadoCategoryId) {
        this.mercadoCategoryId = mercadoCategoryId;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

