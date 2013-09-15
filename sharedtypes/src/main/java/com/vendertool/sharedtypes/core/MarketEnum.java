package com.vendertool.sharedtypes.core;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum MarketEnum {
	UNKNOWN(0, "UNKNOWN"), 
	MERCADO_LIBRE(1, "MERCADO_LIBRE"), 
	EBAY(2, "EBAY"), 
	AMAZON(3, "AMAZON");

	public static MarketEnum get(int id) {
		MarketEnum[] values = MarketEnum.values();
		for (MarketEnum typeEnum : values) {
			if (typeEnum.getId() == id) {
				return typeEnum;
			}
		}
		return UNKNOWN;
	}

	private int id;
	private String name;

	MarketEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}