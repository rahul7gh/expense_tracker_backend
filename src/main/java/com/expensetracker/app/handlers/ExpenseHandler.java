package com.expensetracker.app.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Exp;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.expensetracker.app.custom_exceptions.CustomException;
import com.expensetracker.app.custom_exceptions.ExpenseItemNotFoundException;
import com.expensetracker.app.entities.Expense;
import com.expensetracker.app.entities.ExpenseItem;
import com.expensetracker.app.entities.User;
import com.expensetracker.app.repositories.ExpenseRepository;
import com.expensetracker.app.repositories.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class ExpenseHandler {

	@Autowired
	private ExpenseRepository expRepo;

	@Autowired
	private UserRepository userRepo;

	public Mono<ServerResponse> addExpense(ServerRequest serverReq) {
		String email = serverReq.pathVariable("email");
		String dateStr = serverReq.pathVariable("date");
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		return userRepo.findByEmail(email).flatMap(user -> {
			return Mono.zip(Mono.just(user), serverReq.bodyToMono(ExpenseItem.class));
		}).flatMap(tuple -> {
			User user = tuple.getT1();
			ExpenseItem reqBody = tuple.getT2();
			if (user.getExpenseList().stream().filter(e -> e.getDate().isEqual(date)).count() == 0) {
				user.getExpenseList().add(new Expense(null, date, new ArrayList<>(List.of(reqBody))));
			}
			user.getExpenseList().stream().filter(e -> e.getDate().isEqual(date)).forEach(list -> {
				list.getExpenseItems().add(reqBody);
			});
			return userRepo.save(user);
		}).flatMap(result -> ServerResponse.ok().body(userRepo.findAll(), User.class));
	}

	public Mono<ServerResponse> findExpensesOfDay(ServerRequest serverReq) {
		String email = serverReq.pathVariable("email");
		String dateStr = serverReq.pathVariable("date");
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		return userRepo.findByEmail(email).flatMap(user -> {

			List<Expense> collectList = user.getExpenseList().stream().filter(item -> item.getDate().isEqual(date))
					.collect(Collectors.toList());

			if (collectList.size() == 0)
				return Mono.error(new ExpenseItemNotFoundException("Data Not Found!"));
			return Mono.just(collectList.get(0));

		}).flatMap(result -> ServerResponse.ok().body(Mono.just(result), ExpenseItem.class));
	}

	public Mono<ServerResponse> deleteAllExpensesOfDay(ServerRequest serverReq) {

		String email = serverReq.pathVariable("email");
		String dateStr = serverReq.pathVariable("date");
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		return userRepo.findByEmail(email).flatMap(user -> {

			List<Expense> collectList = user.getExpenseList().stream().filter(item -> item.getDate().isEqual(date))
					.collect(Collectors.toList());

			if (collectList.size() == 0)
				return Mono.error(new ExpenseItemNotFoundException("Data Not Found!"));

			Mono<Expense> result = Mono.just(collectList.get(0));

			user.getExpenseList().removeIf(item -> item.getDate().isEqual(date));

			return userRepo.save(user);

		}).flatMap(result -> ServerResponse.ok().body(Mono.just(result), ExpenseItem.class));
	}

	public Mono<ServerResponse> deleteExpensesOfDayByName(ServerRequest serverReq) {

		String email = serverReq.pathVariable("email");
		String dateStr = serverReq.pathVariable("date");
		String itemName = serverReq.pathVariable("name");
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		return userRepo.findByEmail(email).flatMap(user -> {

			List<Expense> collectList = user.getExpenseList().stream().filter(item -> item.getDate().isEqual(date))
					.collect(Collectors.toList());

			if (collectList.size() == 0)
				return Mono.error(new ExpenseItemNotFoundException("Data Not Found!"));

			
			collectList.get(0).getExpenseItems().removeIf(item->item.getName().equals(itemName));
			user.setExpenseList(collectList);

			return userRepo.save(user);

		}).flatMap(result -> ServerResponse.ok().body(Mono.just(result), User.class));

	}
}
