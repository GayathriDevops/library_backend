package com.hcl.service;

import javax.validation.Valid;

import com.hcl.dto.BarrowedReqDto;
import com.hcl.dto.LoginReqDto;
import com.hcl.dto.LoginResDto;
import com.hcl.dto.RegisterReqDto;
import com.hcl.dto.ResponseDto;
/**
 * 
 * @author Pradeep AJ
 *
 */

public interface UserService {

	ResponseDto createNewUser(@Valid RegisterReqDto registerReqDto);

	LoginResDto login(@Valid LoginReqDto loginReqDto);

	ResponseDto barrowBook(BarrowedReqDto requestDto);

	ResponseDto requestBook(BarrowedReqDto requestDto);


}
