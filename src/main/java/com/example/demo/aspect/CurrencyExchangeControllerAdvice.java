package com.example.demo.aspect;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.controller.ErrorMessageType;
import com.example.demo.controller.ErrorResponse;
import com.example.demo.exception.RequestedResourceNotFoundException;
import com.example.demo.exception.ValidationException;

@ControllerAdvice
public class CurrencyExchangeControllerAdvice {

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse processValidationException(ValidationException ex) {
		ErrorResponse response = new ErrorResponse("from and to", ex.getMessage(),ErrorMessageType.WARNING);
		return response;
	}
	
	@ExceptionHandler(RequestedResourceNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleRequestedResoureNotFoundException(RequestedResourceNotFoundException ex) {
		ErrorResponse res = new ErrorResponse("DB", ex.getMessage(), ErrorMessageType.INFO);
		return res;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
		ErrorResponse res = new ErrorResponse(ex.getConstraintViolations().toString(), ex.getMessage(), ErrorMessageType.ERROR);
		return res;
	}
}
