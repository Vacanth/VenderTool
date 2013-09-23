package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum SecurityQuestionCodeEnum {
	FIRST_OWNED_PET(0, "SECQUESTION.FIRST_OWNED_PET"),
	FAVORITE_SCHOOL_TEACHER(1, "SECQUESTION.FAVORITE_SCHOOL_TEACHER"),
	FIRST_OWNED_CAR(2, "SECQUESTION.FIRST_OWNED_CAR"),
	GREWUP_CITY(3, "SECQUESTION.GREWUP_CITY"),
	NAME_OF_YOUR_HIGH_SCHOOL(4, "SECQUESTION.NAME_OF_YOUR_HIGH_SCHOOL"),
	FAVORITE_MOVIE_STAR(5, "SECQUESTION.FAVORITE_MOVIE_STAR"),
	FAVORITE_AUTHOR_NAME(6, "SECQUESTION.FAVORITE_AUTHOR_NAME"),
	FAVORITE_VACATION_DESTINATION(7, "SECQUESTION.FAVORITE_VACATION_DESTINATION"),
	YOUR_HOROSCOPE_SIGN(8, "SECQUESTION.YOUR_HOROSCOPE_SIGN"),
	FAVORITE_SPORTS_TEAM_NAME(9, "SECQUESTION.FAVORITE_SPORTS_TEAM_NAME");
	
	private String code;
	private int index;
	
	private SecurityQuestionCodeEnum(int id, String code) {
		this.index = id;
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public int getIndex() {
		return index;
	}

	public static SecurityQuestionCodeEnum get(String code) {
		if((code == null) || (code.trim().isEmpty())) {
			return null;
		}
		
		SecurityQuestionCodeEnum[] questionEnums = SecurityQuestionCodeEnum.values();
		for(SecurityQuestionCodeEnum questionEnum : questionEnums) {
			if(questionEnum.getCode().equalsIgnoreCase(code)) {
				return questionEnum;
			}
		}
		
		return null;
	}
	
	public static SecurityQuestionCodeEnum getByIndex(int index) {
		if(index < 0) {
			return null;
		}
		
		SecurityQuestionCodeEnum[] questionEnums = SecurityQuestionCodeEnum.values();
		for(SecurityQuestionCodeEnum questionEnum : questionEnums) {
			if(questionEnum.getIndex() == index) {
				return questionEnum;
			}
		}
		
		return null;
	}
	
	public String toString() {
		return getCode();
	}
}
