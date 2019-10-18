package com.hcl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author Pradeepa AJ
 *
 */

@Configuration

public class WebMvcConfig implements WebMvcConfigurer {
	
	/**
	 * method will return BCryptPasswordEncoder object
	 * @return- BCryptPasswordEncoder
	 */

	@Bean

	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

		 
	}

}
