//package com.expensetracker.app.exception_handler;
//
//import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
//import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
//import org.springframework.boot.web.reactive.error.ErrorAttributes;
//import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.RequestPredicate;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import org.springframework.web.server.ServerWebExchange;
//
//import reactor.core.publisher.Mono;
//
//@Component
//public class GlobalexceptionHandler implements ErrorWebExceptionHandler {
//
//	@Override
//	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//		DataBuffer wrap = exchange.getResponse().bufferFactory().wrap(ex.getMessage().getBytes());
//		
//		exchange.getResponse().writeWith(Mono.just(wrap));
//		return null;
//	}
//
//
//	
//}
//
