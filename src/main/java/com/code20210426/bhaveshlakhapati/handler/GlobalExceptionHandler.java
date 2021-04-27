package com.code20210426.bhaveshlakhapati.handler;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<?> handleException(final WebExchangeBindException exception) {
		Map<String, String> fieldErrorMap = exception.getFieldErrors()
			.stream()
			.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		
		return ResponseEntity.badRequest().body(fieldErrorMap);
	}
}
