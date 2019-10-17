package com.hcl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.hcl.dto.ResponseDto;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BookNotPresentException.class)
	public ResponseEntity<ResponseError> bookNotPresentException(BookNotPresentException ex) {
		ResponseError error = new ResponseError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserExitsException.class)
	public ResponseEntity<ResponseDto> employeeNotFoundExceptionExceptionHandler(UserExitsException ex,
			WebRequest request) {
		ResponseDto responseDto = ResponseDto.builder().message(ex.getMessage()).statusCode(HttpStatus.FOUND.value())
				.build();
		return new ResponseEntity<>(responseDto, HttpStatus.FOUND);

	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseDto> employeeNotFoundExceptionExceptionHandler(InvalidCredentialsException ex,
			WebRequest request) {
		ResponseDto responseDto = ResponseDto.builder().message(ex.getMessage()).statusCode(HttpStatus.UNAUTHORIZED.value())
				.build();
		return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);

	}
	

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ResponseDto> dataNotFoundExceptionExceptionHandler(DataNotFoundException ex,
			WebRequest request) {
		ResponseDto responseDto = ResponseDto.builder().message(ex.getMessage()).statusCode(HttpStatus.NOT_FOUND.value())
				.build();
		return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);

	}

}