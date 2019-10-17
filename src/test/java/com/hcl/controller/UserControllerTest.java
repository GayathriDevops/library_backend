package com.hcl.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import com.hcl.dto.BarrowedReqDto;

import com.hcl.dto.LoginReqDto;

import com.hcl.dto.LoginResDto;

import com.hcl.dto.RegisterReqDto;

import com.hcl.dto.ResponseDto;

import com.hcl.service.UserService;

import com.hcl.util.Constants;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)

public class UserControllerTest {

	@Mock

	private UserService userService;

	@InjectMocks

	private UserController userController;

	private LoginReqDto loginReq;

	private LoginResDto loginRes;

	private RegisterReqDto registerReqDto;

	private ResponseDto response;

	private BarrowedReqDto barrowedReqDto;

	@Before

	public void setUp() {

		MockitoAnnotations.initMocks(this);

		barrowedReqDto = BarrowedReqDto.builder().userId(1L).bookId(1L).endDate(LocalDate.now())

				.fromDate(LocalDate.now()).build();

		loginReq = LoginReqDto.builder().email("Pradeep.aj28@gmail.com").password("Pradeep").build();

		loginRes = LoginResDto.builder().message(Constants.LOG_SUCCESS_MESSAGE).statusCode(Constants.OK).userId(1L)

				.build();

		registerReqDto = RegisterReqDto.builder().build();

		response = ResponseDto.builder().message("Login Success").statusCode(201).build();

	}

	@Test

	public void loginTest() {

		Mockito.when(userService.login(loginReq)).thenReturn(loginRes);

		ResponseEntity<LoginResDto> actRes = userController.login(loginReq);

		assertEquals(HttpStatus.OK, actRes.getStatusCode());

	}

	@Test

	public void createNewUserTest() {

		Mockito.when(userService.createNewUser(registerReqDto)).thenReturn(response);

		ResponseEntity<ResponseDto> actRes = userController.createNewUser(registerReqDto);

		assertEquals(HttpStatus.CREATED, actRes.getStatusCode());

	}

	@Test

	public void borrowBookTest() {

		Mockito.when(userService.barrowBook(barrowedReqDto)).thenReturn(response);

		ResponseEntity<ResponseDto> actRes = userController.barrowBook(barrowedReqDto);

		assertEquals(HttpStatus.CREATED, actRes.getStatusCode());

	}

	@Test

	public void requestBookTest() {

		Mockito.when(userService.requestBook(barrowedReqDto)).thenReturn(response);

		ResponseEntity<ResponseDto> actRes = userController.requestBook(barrowedReqDto);

		assertEquals(HttpStatus.CREATED, actRes.getStatusCode());

	}

}