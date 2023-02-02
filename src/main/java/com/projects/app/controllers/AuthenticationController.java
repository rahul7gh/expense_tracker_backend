package com.projects.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.projects.app.custom_exceptions.CustomException;
import com.projects.app.dto.AuthenticationRequest;
import com.projects.app.dto.ValidationResponse;

import reactor.core.publisher.Mono;

@RestController
public class AuthenticationController {

	@PostMapping("/validate-token")
	public Mono<ResponseEntity<ValidationResponse>> validateGoogleSignIn(@RequestBody AuthenticationRequest authReq) {

		WebClient webc = WebClient.builder().baseUrl("https://oauth2.googleapis.com").build();
		
		return webc.get()
		.uri(builder -> builder.path("/tokeninfo").queryParam("id_token", authReq.getCredentials()).build())
		.retrieve()
		.bodyToMono(ValidationResponse.class)
		.map(data->ResponseEntity.ok(data))
		.onErrorMap(ex->new RuntimeException("Yosh!"));
		
		
			


//				.map(result -> ResponseEntity.ok(result));
	}

}
