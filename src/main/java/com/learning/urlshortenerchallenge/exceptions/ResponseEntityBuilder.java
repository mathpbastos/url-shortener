package com.learning.urlshortenerchallenge.exceptions;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

	public static ResponseEntity<Object> build(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
}
