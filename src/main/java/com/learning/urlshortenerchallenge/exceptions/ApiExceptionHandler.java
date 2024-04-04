package com.learning.urlshortenerchallenge.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(UrlExpiredException.class)
	protected ResponseEntity<Object> handleUrlExpiredException(UrlExpiredException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		ApiError error = 
				new ApiError(
						LocalDateTime.now(),
						HttpStatus.GONE,
						"URL expired",
						details
				);
		
		return ResponseEntityBuilder.build(error);
	}

	@ExceptionHandler(UrlNotFoundException.class)
	protected ResponseEntity<Object> handleUrlNotFoundException(UrlNotFoundException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		ApiError error = 
				new ApiError(
						LocalDateTime.now(),
						HttpStatus.NOT_FOUND,
						"URL not found",
						details
				);
		
		return ResponseEntityBuilder.build(error);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details = ex.getBindingResult()
					.getFieldErrors()
					.stream()
					.map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList());
		
		ApiError apiError = 
				new ApiError(
						LocalDateTime.now(),
						HttpStatus.BAD_REQUEST,
						"Malformed JSON request",
						details
				);
		
		return ResponseEntityBuilder.build(apiError);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	protected ResponseEntity<Object> handleSQLIntegretityConstraintViolation(SQLIntegrityConstraintViolationException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		ApiError apiError = 
				new ApiError(
						LocalDateTime.now(),
						HttpStatus.BAD_REQUEST,
						"URL already exists",
						details
				);
		
		return ResponseEntityBuilder.build(apiError);
	}
		
	
}
