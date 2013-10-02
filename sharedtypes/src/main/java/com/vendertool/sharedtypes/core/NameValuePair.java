package com.vendertool.sharedtypes.core;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class NameValuePair {
	private String name;
	private List<String> values;
	private Long refId;
	private RefTypeEnum refType;
	
	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public RefTypeEnum getRefType() {
		return refType;
	}

	public void setRefType(RefTypeEnum refType) {
		this.refType = refType;
	}

	public NameValuePair(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
}
