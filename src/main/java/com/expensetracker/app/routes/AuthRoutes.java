package com.expensetracker.app.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.expensetracker.app.custom_exceptions.GoogleSignInException;
import com.expensetracker.app.dto.ApiErrorResponse;
import com.expensetracker.app.handlers.AuthHandler;

import reactor.core.publisher.Mono;

@Configuration
public class AuthRoutes {

	@Autowired
	private AuthHandler authHandler;

	@Bean
	public RouterFunction<ServerResponse> auth() {

		return RouterFunctions.route()
				.POST("/validate-token", authHandler::validateGoogleSignInToken)
				.onError(GoogleSignInException.class, (ex, serverReq) -> {
					System.err.println(ex);
					return ServerResponse.status(ex.getStatusCode())
							.body(Mono.just(new ApiErrorResponse(ex.getStatusCode(),ex.getMessage())), ApiErrorResponse.class);
//							.build();
//							.body(ex, Throwable.class);
				})
				.build();
	}
}
