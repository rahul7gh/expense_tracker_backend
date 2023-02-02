//package com.projects.app.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
////	@Bean
////	public MapReactiveUserDetailsService userDetailsService() {
////		UserDetails user = User.withDefaultPasswordEncoder()
////			.username("user")
////			.password("user")
////			.roles("USER")
////			.build();
////		return new MapReactiveUserDetailsService(user);
////	}
//
////	@Bean
////	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////		http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated()).oauth2Login();
//////			.httpBasic();
////		return http.build();
////	}
//
////	@Bean
////	public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
////		httpSecurity.csrf().disable().antMatcher("/**").authorizeRequests()
////				.anyRequest().authenticated().and().oauth2Login().permitAll();
////		return httpSecurity.build();
////	}
//
//}
