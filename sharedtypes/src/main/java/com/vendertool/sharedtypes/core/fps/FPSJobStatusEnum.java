package com.vendertool.sharedtypes.core.fps;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FPSJobStatusEnum {
	UNKNOWN(-1),
	CREATED(1),
	IN_PROCESS(2),
	SUCCESS(3),
	FAILED(4),
	PARTIAL_SUCCESS(5), 
	ABANDONED(6);
		
	private int id;

	private FPSJobStatusEnum(int id) {
		this.id = id; 
	}

	public int getId() {
		return id;
	}

	public static FPSJobStatusEnum get(int id) {
		FPSJobStatusEnum[] values = FPSJobStatusEnum.values();
		for (FPSJobStatusEnum typeEnum : values) {
			if (typeEnum.getId() == id) {
				return typeEnum;
			}
		}
		return UNKNOWN;
	}
}