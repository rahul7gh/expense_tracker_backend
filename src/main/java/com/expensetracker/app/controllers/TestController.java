package com.expensetracker.app.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.app.dto.AuthenticationRequest;
import com.expensetracker.app.entities.User;
import com.expensetracker.app.repositories.UserRepository;

import reactor.core.publisher.Mono;

@RestController
public class TestController {

	@Autowired
	UserRepository userRepo; 
	
	@GetMapping("/")
	public Mono<String> tesHello()
	{
		return Mono.just("Yosh");
	}
	
	@PostMapping("/test")
	public Mono<AuthenticationRequest> demo(@RequestBody AuthenticationRequest authReq)
	{
		System.err.println(authReq);
		return Mono.just(authReq);
	}
	@PostMapping("/user")
	public Mono<User> demo()
	{
		User user = new User(null, "rahul@gmail.com", "1234", new ArrayList<>());
		return userRepo.save(user);
	}
	
	public int add(int a,int b)
	{
		return a+b;
	}
}
