package com.hcl.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.BarrowedReqDto;
import com.hcl.dto.LoginReqDto;
import com.hcl.dto.LoginResDto;
import com.hcl.dto.RegisterReqDto;
import com.hcl.dto.ResponseDto;
import com.hcl.service.UserService;

/**
 * 
 * @author Pradeep AJ
 *method1-createNewUser()-Check useremail is exists is not save new user into the table  
 *method2-login()-check user exits and validate with data is true return userId
 *method3-barrowBook()-check book is avalaible if there save data to borrowed book
 *method4-requestBook()-saving request book
 *
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @param registerReqDto
	 * @return ResponseDto
	 */
	@PostMapping("/users/registration")
	public ResponseEntity<ResponseDto> createNewUser(@Valid @RequestBody RegisterReqDto registerReqDto){
		logger.info("Enter into UserController::---------- createNewUser()");
		return new ResponseEntity<>(userService.createNewUser(registerReqDto),HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @param loginReqDto
	 * @return LoginResDto
	 */
	@PostMapping("/users/login")
	public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto loginReqDto){
		logger.info("Enter into UserController::---------- login()");
		return new ResponseEntity<>(userService.login(loginReqDto),HttpStatus.OK);
	}
	/**
	 * 
	 * @param requestDto-NotNull
	 * @return ResponseDto
	 */
	@PostMapping("/books/borrow")
	public ResponseEntity<ResponseDto> barrowBook(@RequestBody BarrowedReqDto requestDto){
		logger.info("Enter into UserController::---------- barrowBook()");
		return new ResponseEntity<>(userService.barrowBook(requestDto),HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @param requestDto-NotNull
	 * @return ResponseDto
	 */
	@PostMapping("/books/request")
	public ResponseEntity<ResponseDto> requestBook(@RequestBody BarrowedReqDto requestDto){
		logger.info("Enter into UserController::---------- requestBook()");
		return new ResponseEntity<>(userService.requestBook(requestDto),HttpStatus.CREATED);
	}
}
