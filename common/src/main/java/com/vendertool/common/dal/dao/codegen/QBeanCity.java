package com.vendertool.common.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanCity is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanCity {

    private Long cityId;

    private String cityName;

    private java.sql.Timestamp createdDate;

    private String iso3CountryCode;

    private java.sql.Timestamp lastModifiedDate;

    private Long state;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getIso3CountryCode() {
        return iso3CountryCode;
    }

    public void setIso3CountryCode(String iso3CountryCode) {
        this.iso3CountryCode = iso3CountryCode;
    }

    public java.sql.Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.sql.Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

}

