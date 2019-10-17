package com.hcl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration

@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Override

	protected void configure(AuthenticationManagerBuilder auth)

			throws Exception {

		
	}

	@Override

	protected void configure(HttpSecurity http) throws Exception {

//		http.
//
//				authorizeRequests()

//				.antMatchers("/").permitAll()
//
//				.antMatchers("/login").permitAll()
//
//				.antMatchers("/registration").permitAll()
//
//				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
//
//				.authenticated().and().csrf().disable().formLogin()
//
//				.loginPage("/login").failureUrl("/login?error=true")
//
//				.defaultSuccessUrl("/admin/home")
//
//				.usernameParameter("email")
//
//				.passwordParameter("password")
//
//				.and().logout()
//
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//
//				.logoutSuccessUrl("/").and().exceptionHandling()
//
//				.accessDeniedPage("/access-denied");
		http .csrf().disable() .authorizeRequests() .anyRequest().permitAll(); 
	}

	@Override

	public void configure(WebSecurity web) throws Exception {

		web

				.ignoring()

				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");

	}

}