package com.vendertool.sharedtypes.core.fps;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FPSFileStatusEnum {
	UNKNOWN(-1),
	CREATED(1),
	IN_PROGRESS(2),
	SUCCESS(3),
	FAILED(4),
	PARTIAL_SUCCESS(5), 
	ABANDONED(6);
	
	private int id;

	private FPSFileStatusEnum(int id) {
		this.id = id; 
	}

	public int getId() {
		return id;
	}

	public static FPSFileStatusEnum get(int id) {
		FPSFileStatusEnum[] values = FPSFileStatusEnum.values();
		for (FPSFileStatusEnum typeEnum : values) {
			if (typeEnum.getId() == id) {
				return typeEnum;
			}
		}
		return UNKNOWN;
	}
}
