package com.expensetracker.app.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.expensetracker.app.custom_exceptions.CustomException;
import com.expensetracker.app.custom_exceptions.GoogleSignInException;
import com.expensetracker.app.dto.AuthenticationRequest;
import com.expensetracker.app.dto.ValidationResponse;

import reactor.core.publisher.Mono;

@Service
public class AuthHandler {

	@Autowired
	private WebClient webClient;

	public Mono<ServerResponse> validateGoogleSignInToken(ServerRequest serverReq) {

		return serverReq.bodyToMono(AuthenticationRequest.class).flatMap(reqBody -> {
			return webClient.get().uri(builder -> {
				return builder.path("/tokeninfo").queryParam("id_token", reqBody.getCredentials()).build();
			})
					.retrieve().onStatus(status -> status.is4xxClientError(), (clientResponse) -> {
						return clientResponse.createException()
								.map(ex -> new GoogleSignInException("Invalid SignIn Token!",ex));
					})
					.onStatus(status -> status.is5xxServerError(), (clientResponse) -> {
						return clientResponse.createException()
								.map(ex -> new GoogleSignInException("Unable To Verify SignIn Token!",ex));
					})
					.bodyToMono(ValidationResponse.class).flatMap(validationResponse -> {
						return ServerResponse.ok().body(Mono.just(validationResponse), ValidationResponse.class);
					});
			
		});

	}
}
