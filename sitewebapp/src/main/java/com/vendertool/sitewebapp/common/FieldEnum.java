package com.vendertool.sitewebapp.common;


public enum FieldEnum {

	EMAIL("email"),
	PASSWORD("password"),
	PASSWORD_CONFIRM("passwordConfirm"),
	FIRST_NAME("fname"),
	LAST_NAME("lname"),
	EMAIL_ALTERNATE("emailAlt"),
	ADDRESS_LINE_1("addressLine1"),
	ADDRESS_LINE_2("addressLine2"),
	CITY("city"),
	STATE("state"),
	ZIP("zip"),
	COUNTRY("country"),
	PHONE_WORK("phoneWork"),
	PHONE_MOBILE("phoneMobile"),
	PHONE_HOME("phoneHome")
	
	;
	
	private String name;
	
	private FieldEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
