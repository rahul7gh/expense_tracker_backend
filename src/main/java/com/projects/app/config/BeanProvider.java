package com.projects.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanProvider {

	@Bean
	public WebClient getWebClient()
	{
		return WebClient.create("https://https://oauth2.googleapis.com");
	}
}
