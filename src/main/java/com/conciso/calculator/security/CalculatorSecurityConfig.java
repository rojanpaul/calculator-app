package com.conciso.calculator.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CalculatorSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * Purpose : Users are defined here ADMIN has access to all methods. USER has 
	 * access to add & subtract methods.
	 * Technical note : Users and roles created in this method. Production scenario
	 * uses persistent datastore for saving username or password.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles("USER", "ADMIN");

	}
	
	/**
	 * Purpose : Basic validation is defined here using username and password
	 * Technical note : Every request will be validated for any of users defined.
	 * If no user specified 401 is returned and if existing user without privilege
	 * requests then 403 forbidden message is sent.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// HTTP Basic authentication
			.httpBasic().and().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/calculatorservice/add").hasRole("USER")
			.antMatchers(HttpMethod.POST, "/calculatorservice/subtract").hasRole("USER")
			.antMatchers(HttpMethod.POST, "/calculatorservice/multiply").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/calculatorservice/divide").hasRole("ADMIN")
//			.antMatchers(URL3).permitAll()
			.and().csrf().disable().formLogin()
			.disable();
		
//		http.authorizeRequests().anyRequest().authenticated().and()
//        .x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)").userDetailsService(userDetailsService());
	}

	/*
	 * @Bean public UserDetailsService userDetailsService() { //ok for demo
	 * User.UserBuilder users = User.withDefaultPasswordEncoder();
	 * 
	 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 * manager.createUser(users.username("user").password("password").roles("USER").
	 * build());
	 * manager.createUser(users.username("admin").password("password").roles("USER",
	 * "ADMIN").build()); return manager; }
	 */
}
