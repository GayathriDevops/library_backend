package com.hcl.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.LoginReqDto;
import com.hcl.dto.RegisterReqDto;
import com.hcl.dto.ResponseDto;
import com.hcl.service.UserService;

/**
 * 
 * @author Pradeep AJ
 *
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registration")
	public ResponseEntity<ResponseDto> createNewUser(@Valid @RequestBody RegisterReqDto registerReqDto){
		return new ResponseEntity<>(userService.createNewUser(registerReqDto),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginReqDto loginReqDto){
		return new ResponseEntity<>(userService.login(loginReqDto),HttpStatus.OK);
	}

}
