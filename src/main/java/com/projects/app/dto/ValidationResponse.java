package com.projects.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ValidationResponse {

	private String iss;
	private String nbf;
	private String aud;
	private String sub;
	private String email;
	private String email_verified;
	private String azp;
	private String name;
	private String picture;
	private String given_name;
	private String family_name;
	private String iat;
	private String exp;
	private String jti;
	private String alg;
	private String kid;
	private String typ;

}
