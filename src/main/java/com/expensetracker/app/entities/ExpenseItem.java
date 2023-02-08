package com.expensetracker.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ExpenseItem {
	@Id
	private String id;
	private String name;
	private double price;
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	
	
	
}
