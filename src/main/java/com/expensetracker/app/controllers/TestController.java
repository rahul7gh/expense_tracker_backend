package com.expensetracker.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class TestController {

	@GetMapping
	public Mono<String> tesHello()
	{
		return Mono.just("Yosh");
	}
}
