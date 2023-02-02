package com.projects.app.exception_handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.projects.app.custom_exceptions.CustomException;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public Mono<ResponseEntity<?>> handleException(CustomException e)
	{
		System.err.println(e);
		return Mono.just(ResponseEntity.status(e.getStatusCode()).body(e));
		
	}
}
