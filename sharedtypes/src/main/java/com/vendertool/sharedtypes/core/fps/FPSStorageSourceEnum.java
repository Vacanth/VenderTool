package com.vendertool.sharedtypes.core.fps;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FPSStorageSourceEnum {
	UNKNOWN(-1),
	LOCAL(1),
	AWS_CLOUD(2),
	MS_CLOUD(3);
	
	private int id;

	private FPSStorageSourceEnum(int id) {
		this.id = id; 
	}

	public int getId() {
		return id;
	}

	public static FPSStorageSourceEnum get(int id) {
		FPSStorageSourceEnum[] values = FPSStorageSourceEnum.values();
		for (FPSStorageSourceEnum typeEnum : values) {
			if (typeEnum.getId() == id) {
				return typeEnum;
			}
		}
		return UNKNOWN;
	}
}
