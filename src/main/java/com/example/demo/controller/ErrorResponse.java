package com.example.demo.controller;


public class ErrorResponse {
	private String field;
	private String errorMessage;
	private ErrorMessageType errorMessageType;		
	
	public ErrorResponse(String field, ErrorMessageType errorMessageType) {
		new ErrorResponse(field, "", errorMessageType);
	}

	public ErrorResponse(String field, String errorMessage, ErrorMessageType errorMessageType) {
		super();
		this.field = field;
		this.errorMessage = errorMessage;
		this.errorMessageType = errorMessageType;
	}

	public String getField() {
		return field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ErrorMessageType getErrorMessageType() {
		return errorMessageType;
	}		
	
}
