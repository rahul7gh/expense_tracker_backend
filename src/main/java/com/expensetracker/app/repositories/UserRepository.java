package com.expensetracker.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.expensetracker.app.entities.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {

	Mono<User> findByEmail(String email);
}
