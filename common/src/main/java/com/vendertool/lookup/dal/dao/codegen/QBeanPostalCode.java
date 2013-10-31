package com.vendertool.lookup.dal.dao.codegen;

import javax.annotation.Generated;

/**
 * QBeanPostalCode is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class QBeanPostalCode {

    private Long city;

    private String iso3CountryCode;

    private String postalCode;

    private Integer postalCodeId;

    private Long state;

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getIso3CountryCode() {
        return iso3CountryCode;
    }

    public void setIso3CountryCode(String iso3CountryCode) {
        this.iso3CountryCode = iso3CountryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getPostalCodeId() {
        return postalCodeId;
    }

    public void setPostalCodeId(Integer postalCodeId) {
        this.postalCodeId = postalCodeId;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

}

