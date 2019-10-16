package com.hcl.service;

import javax.validation.Valid;

import com.hcl.dto.LoginReqDto;
import com.hcl.dto.RegisterReqDto;
import com.hcl.dto.ResponseDto;

public interface UserService {

	ResponseDto createNewUser(@Valid RegisterReqDto registerReqDto);

	ResponseDto login(@Valid LoginReqDto loginReqDto);


}
