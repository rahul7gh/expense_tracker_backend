package com.expensetracker.app.custom_exceptions;

public class ExpenseItemNotFoundException extends RuntimeException{

	public ExpenseItemNotFoundException(String msg)
	{
		super(msg);
	}
}
