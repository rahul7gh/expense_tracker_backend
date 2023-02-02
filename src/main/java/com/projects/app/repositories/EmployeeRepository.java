package com.projects.app.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.projects.app.entities.Employee;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee,Long> {

}
