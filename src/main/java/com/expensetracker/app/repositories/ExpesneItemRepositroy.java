package com.expensetracker.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.expensetracker.app.entities.ExpenseItem;

public interface ExpesneItemRepositroy extends ReactiveMongoRepository<ExpenseItem, Long> {

}
