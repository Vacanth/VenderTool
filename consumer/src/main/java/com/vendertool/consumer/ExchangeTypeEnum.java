package com.vendertool.consumer;

public enum ExchangeTypeEnum {
	DIRECT  (1, "direct"),
	TOPIC   (2, "topic"),
	HEADERS (3, "headers"), 
	FANOUT  (4, "fanout");
	
	private int id;
	private String exchangeType;
	
	ExchangeTypeEnum(int id, String value) {
		this.id = id;
		this.exchangeType = value;
	}

	public int getId() {
		return id;
	}

	public String getExchangeType() {
		return exchangeType;
	}
	
	public static ExchangeTypeEnum get(int id) {
		ExchangeTypeEnum[] values = ExchangeTypeEnum.values();
		for(ExchangeTypeEnum e : values) {
			if(e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public static ExchangeTypeEnum get(String value) {
		if((value == null) || (value.trim().isEmpty())) {
			return null;
		}
		ExchangeTypeEnum[] values = ExchangeTypeEnum.values();
		for(ExchangeTypeEnum e : values) {
			if(e.getExchangeType().equalsIgnoreCase(value)) {
				return e;
			}
		}
		return null;
	}
}