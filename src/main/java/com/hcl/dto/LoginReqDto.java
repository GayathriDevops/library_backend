package com.hcl.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginReqDto {

	@Email(message = "*Please provide a valid Email")

	@NotEmpty(message = "*Please provide an email")

	private String email;

	@Length(min = 5, message = "*Your password must have at least 5 characters")

	@NotEmpty(message = "*Please provide your password")

	private String password;

}
