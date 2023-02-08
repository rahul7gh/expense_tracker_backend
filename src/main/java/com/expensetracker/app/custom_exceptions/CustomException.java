package com.expensetracker.app.custom_exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

	private Date timestamp; 
	private HttpStatus statusCode;
	public CustomException(String msg)
	{
		super(msg);
		timestamp=new Date();
	}
	
}
