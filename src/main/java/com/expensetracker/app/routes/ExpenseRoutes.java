package com.expensetracker.app.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.expensetracker.app.custom_exceptions.ExpenseItemNotFoundException;
import com.expensetracker.app.dto.ApiErrorResponse;
import com.expensetracker.app.handlers.ExpenseHandler;
import com.mongodb.internal.connection.Server;

import reactor.core.publisher.Mono;

@Configuration
public class ExpenseRoutes {

	@Autowired
	private ExpenseHandler expenseHandler;

	@Bean
	public RouterFunction<ServerResponse> addNewexpense() {
		return RouterFunctions.route().POST("/{email}/{date}", expenseHandler::addExpense)
				.GET("/{email}/{date}", expenseHandler::findExpensesOfDay)
				.DELETE("/{email}/{date}", expenseHandler::deleteAllExpensesOfDay)
				.DELETE("/{email}/{date}/{name}", expenseHandler::deleteExpensesOfDayByName)
				.onError(ExpenseItemNotFoundException.class, (ex, serverReq) -> {
					return ServerResponse.status(404).body(Mono.just(new ApiErrorResponse(404, ex.getMessage())),
							ApiErrorResponse.class);
				}).build();
	}
}
