package com.vendertool.mercadolibreadapter.add;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
	private String message;
	private String error;
	private int status;
	List<ErrorDetail> cause;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<ErrorDetail> getCause() {
		return cause;
	}
	public void setCause(List<ErrorDetail> cause) {
		this.cause = cause;
	}
}