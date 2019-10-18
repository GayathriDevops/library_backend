package com.hcl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Pradeepa AJ
 * this class used to give Security access , validating to application
 * extends- WebSecurityConfigurerAdapter-overriding all methods
 * @exception -Exception-handle both rchecked and unchecked exceptions
 *
 */
@Configuration

@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Override

	protected void configure(AuthenticationManagerBuilder auth)

			throws Exception {

		 // Do nothing .
	}
	
	/**
	 * Restricting  API permission based on user roles 
	 * @param HttpSecurity
	 * @return void
	 */

	@Override

	protected void configure(HttpSecurity http) throws Exception {

		http .csrf().disable() .authorizeRequests() .anyRequest().permitAll(); 
	}

	/**
	 * only admin can access this resources
	 * @param-WebSecurity 
	 * @return -void
	 */
	@Override

	public void configure(WebSecurity web) throws Exception {

		web

				.ignoring()

				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");

	}

}