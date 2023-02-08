package com.expensetracker.app.custom_exceptions;

import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoogleSignInException extends RuntimeException {

//	private WebClientResponseException ex;
	private int statusCode;
	private String statusText;
	public GoogleSignInException(String msg) {
		super(msg);

//		this.statusCode;
	}
	public GoogleSignInException(String msg,WebClientResponseException ex) {
		super(msg);
		this.statusCode=ex.getRawStatusCode();
		setStackTrace(ex.getStackTrace());
		this.statusText=ex.getStatusText();
	}
}
