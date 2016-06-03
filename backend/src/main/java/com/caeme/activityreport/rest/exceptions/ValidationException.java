package com.caeme.activityreport.rest.exceptions;

import java.util.List;

import com.caeme.activityreport.rest.dto.ValidationError;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 7027543915905603094L;

	private List<ValidationError> validationErrors;

	public ValidationException(List<ValidationError> validationErrors) {
		super();
		this.validationErrors = validationErrors;
	}

	public ValidationException() {
		super();
	}
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ValidationException(Throwable throwable) {
		super(throwable);
	}
	
	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

}
