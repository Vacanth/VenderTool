package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum AccountStatusEnum {
	NOT_VERIFIED(1),
	VERIFIED(2),
	CLOSED(3),
	SUSPENDED(4),
	DELINQUENT(5), 
	PASSWORD_EXPIRED(6),
	EMAIL_CHANGE_NOT_VERIFIED(7);
	
	private int id;
	
	private AccountStatusEnum(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public static AccountStatusEnum get(int id) {
		AccountStatusEnum[] enums = AccountStatusEnum.values();
		for(AccountStatusEnum e : enums) {
			if(e.getId() == id) {
				return e;
			}
		}
		return null;
	}
}
