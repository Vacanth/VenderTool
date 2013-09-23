package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlAccessorType(XmlAccessType.FIELD)
public class Phone {
	
	@XmlEnum
	public enum PhoneType {
		MOBILE, HOME, WORK, PUBLIC, FAX;
	}
	
//	private Integer countryCode;
//	private Integer areaCode;
	private Integer number;
	private PhoneType type;
	
	public Phone(){}

//	public Integer getCountryCode() {
//		return countryCode;
//	}
//
//	public void setCountryCode(Integer countryCode) {
//		this.countryCode = countryCode;
//	}
//
//	public Integer getAreaCode() {
//		return areaCode;
//	}
//
//	public void setAreaCode(Integer areaCode) {
//		this.areaCode = areaCode;
//	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}
	
	@JsonIgnore
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("PHONE=[")
			.append("\n\tTYPE=").append(getType())
//			.append("\n\tCOUNTRY CODE=").append(getCountryCode())
//			.append("\n\tAREA CODE=").append(getAreaCode())
			.append("\n\tNUMBER=").append(getNumber());
	
		return sb.toString();
	}
}