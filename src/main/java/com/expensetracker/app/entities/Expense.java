package com.expensetracker.app.entities;

import java.time.LocalDate;
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
public class Expense  {
	@Id
	private String id;
	private LocalDate date;
	private List<ExpenseItem> expenseItems;

	
	@Override
	public boolean equals(Object obj) {
		System.out.println("Checking");
		Expense anObj=(Expense)obj;
		return anObj.getDate().isEqual(date);
//		return super.equals(obj);
	}

	
}


