package com.expensetracker.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.expensetracker.app.entities.Expense;

public interface ExpenseRepository extends ReactiveMongoRepository<Expense, Long> {

}
