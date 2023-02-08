package com.expensetracker.app.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@AllArgsConstructor
@ToString
public class User
{
	@Id
	private String id;
	private String email;
	private String password;
	private List<Expense> expenseList;
	
	
	@Override
	public boolean equals(Object obj) {
	System.err.println("User equals!");
		return super.equals(obj);
	}
}