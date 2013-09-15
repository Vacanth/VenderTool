package com.vendertool.sharedtypes.core.fps;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FPSUsecaseEnum {
	UNKNOWN(-1),
	ADD_PRODUCT(1),
	REVISE_PRODUCT(2),
	DELETE_PRODUCT(3),
	VERIFY_PRODUCT(4),
	ADD_LISTING(11),
	REVISE_LISTING(12),
	DELETE_LISTING(13),
	VERIFY_LISTING(14);
	
	private int id;

	private FPSUsecaseEnum(int id) {
		this.id = id; 
	}

	public int getId() {
		return id;
	}

	public static FPSUsecaseEnum get(int id) {
		FPSUsecaseEnum[] values = FPSUsecaseEnum.values();
		for (FPSUsecaseEnum typeEnum : values) {
			if (typeEnum.getId() == id) {
				return typeEnum;
			}
		}
		return UNKNOWN;
	}
}
