package com.caeme.activityreport.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.caeme.activityreport.rest.dto.ErrorResponse;
import com.caeme.activityreport.rest.exceptions.ValidationException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		logger.error("There was an unexpected error: ", ex);
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
		error.setMessage("Hubo un error inesperado. Por favor contacte a su administrador. Exception: " + ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> validationExceptionHandler(ValidationException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.BAD_REQUEST.name());
		error.setMessage("Hubo errores de validacion. Por favor revise los parametros.");
		error.setErrors(ex.getValidationErrors());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
}
