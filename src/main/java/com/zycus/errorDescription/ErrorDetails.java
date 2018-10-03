package com.zycus.errorDescription;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ErrorDetails {

	public String errorMessage;
	public Object data;

	public ErrorDetails(String errorMessage, Object data) {
		super();
		this.errorMessage = errorMessage;
		this.data = data;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
