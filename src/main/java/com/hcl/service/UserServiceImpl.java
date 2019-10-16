package com.hcl.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.dto.LoginReqDto;
import com.hcl.dto.RegisterReqDto;
import com.hcl.dto.ResponseDto;
import com.hcl.entity.User;
import com.hcl.exception.InvalidCredentialsException;
import com.hcl.exception.UserExitsException;
import com.hcl.repository.UserRepository;
import com.hcl.util.Constants;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired

	public UserServiceImpl(UserRepository userRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.userRepository = userRepository;
   	this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@Override
	public ResponseDto createNewUser(@Valid RegisterReqDto registerReqDto) {
		Optional<User> userExists = userRepository.findByEmail(registerReqDto.getEmail());
		if (!userExists.isPresent()) {
			User user = new User();
			BeanUtils.copyProperties(registerReqDto, user);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			ResponseDto response = ResponseDto.builder().message(Constants.REG_SUCCESS_MESSAGE)
					.statusCode(Constants.CREATED).build();
			return response;
		}
		throw new UserExitsException(Constants.USER_EXISTS);

	}

	@Override
	public ResponseDto login(@Valid LoginReqDto loginReqDto) {
		Optional<User> userExists = userRepository.findByEmail(loginReqDto.getEmail());
		if (userExists.isPresent()) {
			if (userExists.get().getEmail().equals(loginReqDto.getEmail())
					&& bCryptPasswordEncoder.matches(loginReqDto.getPassword(), userExists.get().getPassword())) {
				ResponseDto response = ResponseDto.builder().message(Constants.LOG_SUCCESS_MESSAGE)
						.statusCode(Constants.OK).build();
				return response;
			}
			
		}
		throw new InvalidCredentialsException(Constants.INVALID_CREDENTIALS);
	}

}
