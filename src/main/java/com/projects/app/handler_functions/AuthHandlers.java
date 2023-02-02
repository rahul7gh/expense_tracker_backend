package com.projects.app.handler_functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.projects.app.dto.AuthenticationRequest;
import com.projects.app.dto.ValidationResponse;

import reactor.core.publisher.Mono;

@Service
public class AuthHandlers {

	@Autowired
	private WebClient webClient;

	public Mono<ServerResponse> testHello(ServerRequest request) {
		return ServerResponse.ok().body(Mono.just("Aayeah!"), String.class);
	}

	public Mono<ServerResponse> validateGoogleSignInToken(ServerRequest serverReq) {

//		return ServerResponse.ok().body(serverReq.bodyToMono(AuthenticationRequest.class).flatMap(authReq -> {
//			return webClient.get()
//					.uri(builder -> builder.path("/tokeninfo").queryParam("id_token", authReq.getCredentials()).build())
//					.exchangeToMono(clientResponse -> {
//						return clientResponse.bodyToMono(ValidationResponse.class);
//					});
//		}), ValidationResponse.class);
//		Mono<ValidationResponse> output = null;
//		output = serverReq.bodyToMono(AuthenticationRequest.class).map(authReq -> {
//			return new ValidationResponse();
//		});
//		return ServerResponse.ok().body(output, ValidationResponse.class);
		
		return ServerResponse.ok().body(
				serverReq.bodyToMono(AuthenticationRequest.class).map(authReq -> {
			return webClient.get()
					.uri(builder -> builder.path("/tokeninfo").queryParam("id_token", authReq.getCredentials()).build())
					.exchangeToMono(clientResponse -> {
						return clientResponse.bodyToMono(ValidationResponse.class);
					});
		}).map(elmnt->elmnt.block()), ValidationResponse.class);

	}
}
