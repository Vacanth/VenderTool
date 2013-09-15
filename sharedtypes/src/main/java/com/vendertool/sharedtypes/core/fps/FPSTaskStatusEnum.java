package com.vendertool.sharedtypes.core.fps;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FPSTaskStatusEnum {
	UNKNOWN(-1),
	CREATED(1),
	IN_PROCESS(2),
	SUCCESS(3),
	FAILED(4),
	ABANDONED(5);
	
	private int id;

	private FPSTaskStatusEnum(int id) {
		this.id = id; 
	}

	public int getId() {
		return id;
	}

	public static FPSTaskStatusEnum get(int id) {
		FPSTaskStatusEnum[] values = FPSTaskStatusEnum.values();
		for (FPSTaskStatusEnum typeEnum : values) {
			if (typeEnum.getId() == id) {
				return typeEnum;
			}
		}
		return UNKNOWN;
	}
}
