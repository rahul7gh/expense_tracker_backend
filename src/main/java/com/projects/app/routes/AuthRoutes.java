package com.projects.app.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.projects.app.handler_functions.AuthHandlers;

import reactor.core.publisher.Mono;

@Configuration
public class AuthRoutes {

	@Autowired
	private AuthHandlers authHandler;

	@Bean
	public RouterFunction<ServerResponse> testHello() {
		return RouterFunctions.route().GET("yosh", authHandler::testHello).build();
	}

	@Bean
	public RouterFunction<ServerResponse> heighLevel() {
		return RouterFunctions.route().path("/yosh1", this::testHello).build();
	}

	@Bean
	public RouterFunction<ServerResponse> validateGoogleSignInToken() {
		return RouterFunctions.route().POST("/validate-token", authHandler::validateGoogleSignInToken)
				.onError(Exception.class, (error, serverReq) -> {
					return ServerResponse.status(500).body(Mono.just("Gandla Na Bhai vishay!"), String.class);
				}).build();

	}

//	@Bean
//	public RouterFunction<ServerResponse> GlobalExceptionHandler()
//	{
//		return RouterFunctions.route()
//					}
}
