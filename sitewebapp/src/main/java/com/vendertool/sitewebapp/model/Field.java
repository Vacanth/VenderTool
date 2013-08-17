package com.vendertool.sitewebapp.model;

import java.util.ArrayList;
import java.util.List;

import com.vendertool.sitewebapp.common.FieldEnum;

public class Field {

	private FieldEnum type;
	private String name;
	private String value;
	private List<FieldOption> options = new ArrayList<FieldOption>();
	private boolean hasError;
	private List<String> errorMessages = new ArrayList<String>();
	
	public Field() {
	}
	
	public Field setType(FieldEnum type) {
		this.type = type;
		this.name = type.getName();
		return this;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public Field setValue(String value) {
		this.value = value;
		return this;
	}
	public List<FieldOption> getOptions() {
		return options;
	}
	public void setOptions(List<FieldOption> options) {
		this.options = options;
	}
	public void addOption(FieldOption option) {
		this.options.add(option);
	}
	
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	public Field setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
		return this;
	}
	public Field addErrorMessage(String errorMessage) {
		this.errorMessages.add(errorMessage);
		return this;
	}
	
	
}

