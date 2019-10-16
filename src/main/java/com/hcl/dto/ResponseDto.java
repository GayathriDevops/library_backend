package com.hcl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class ResponseDto {
	private int statusCode;
	private String message;

}
