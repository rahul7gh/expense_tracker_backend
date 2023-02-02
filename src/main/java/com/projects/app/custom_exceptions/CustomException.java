package com.projects.app.custom_exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomException extends RuntimeException {

	private Date date=new Date();
	private int statusCode;
	public CustomException(String msg)
	{
		super(msg);
	}
}

