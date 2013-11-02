package com.vendertool.common.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanMercadoCategoryGroup is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanMercadoCategoryGroup {

    private java.sql.Timestamp createdDate;

    private java.sql.Timestamp groupDate;

    private java.sql.Timestamp lastModifiedDate;

    private Integer mercadoCategoryGroupId;

    private Integer siteId;

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public java.sql.Timestamp getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(java.sql.Timestamp groupDate) {
        this.groupDate = groupDate;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getMercadoCategoryGroupId() {
        return mercadoCategoryGroupId;
    }

    public void setMercadoCategoryGroupId(Integer mercadoCategoryGroupId) {
        this.mercadoCategoryGroupId = mercadoCategoryGroupId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

}

