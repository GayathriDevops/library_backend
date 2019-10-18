package com.hcl.service;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.controller.UserController;
import com.hcl.dto.BarrowedReqDto;
import com.hcl.dto.LoginReqDto;
import com.hcl.dto.LoginResDto;
import com.hcl.dto.RegisterReqDto;
import com.hcl.dto.ResponseDto;
import com.hcl.entity.Book;
import com.hcl.entity.BorrowedBook;
import com.hcl.entity.RequestedBook;
import com.hcl.entity.User;
import com.hcl.exception.BookNotPresentException;
import com.hcl.exception.InvalidCredentialsException;
import com.hcl.exception.UserExitsException;
import com.hcl.repository.BookRepository;
import com.hcl.repository.BorrowedBookRepository;
import com.hcl.repository.RequestedBookRepository;
import com.hcl.repository.UserRepository;
import com.hcl.util.Constants;
/**
 * 
 * @author Pradeep AJ
 *
 */


@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserRepository userRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RequestedBookRepository requestedBookRepository;
	@Autowired
	private BorrowedBookRepository barrowedBookRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired

	public UserServiceImpl(UserRepository userRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.userRepository = userRepository;
   	this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}
	/**
	 * method will check email already exists. If it is available throw the exception 
	 * else save the user
	 * @param registerReqDto- @Valid -NotNull
	 * @return ResponseDto
	 * @exception UserExitsException
	 */

	@Override
	public ResponseDto createNewUser(@Valid RegisterReqDto registerReqDto) {
		Optional<User> userExists = userRepository.findByEmail(registerReqDto.getEmail());
		if (!userExists.isPresent()) {
			logger.info("User Not Exits");
			User user = new User();
			BeanUtils.copyProperties(registerReqDto, user);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return ResponseDto.builder().message(Constants.REG_SUCCESS_MESSAGE)
					.statusCode(Constants.CREATED).build();
			
		}
		throw new UserExitsException(Constants.USER_EXISTS);

	}
	/**
	 * method will check user  with email and password  if exists login success.
	 * else throw exception InvalidCredentialsException
	 * @param LoginReqDto- @Valid -NotNull
	 * @return LoginResDto
	 * @exception InvalidCredentialsException
	 */

	@Override
	public LoginResDto login(@Valid LoginReqDto loginReqDto) {
		Optional<User> userExists = userRepository.findByEmail(loginReqDto.getEmail());
		if (userExists.isPresent()) {
			logger.info("Valid User::----------={}",loginReqDto.getEmail());
			if (userExists.get().getEmail().equals(loginReqDto.getEmail())
					&& bCryptPasswordEncoder.matches(loginReqDto.getPassword(), userExists.get().getPassword())) {
				return LoginResDto.builder().message(Constants.LOG_SUCCESS_MESSAGE)
						.statusCode(Constants.OK).userId(userExists.get().getUserId()).build();
			
			}
			
		}
		throw new InvalidCredentialsException(Constants.INVALID_CREDENTIALS);
	
	}
	/**
	 * saving barrowed book in the borrowedBook table with userId and bookId 
	 * @param requestDto-NotNull
	 * @return ResponseDto
	 */
	
	@Override
	public ResponseDto barrowBook(BarrowedReqDto requestDto) {
		logger.info("UserServiceImpl::----------barrowBook()");
		Optional<Book> book=bookRepository.findById(requestDto.getBookId());
		if(book.isPresent()) {
			Book books=book.get();
			books.setBookStatus(Constants.BOOROWED);
			bookRepository.save(books);
		BorrowedBook borrow=new BorrowedBook();
		BeanUtils.copyProperties(requestDto, borrow);
		barrowedBookRepository.save(borrow);
		return ResponseDto.builder().message(Constants.BOOROWED_BOOK)
				.statusCode(Constants.CREATED).build();
		}
		else throw new BookNotPresentException(Constants.BOOK_NOT_PRESENT);
		
	}
	/**
	 * saving barrowed book in the requestedBook table with userId and bookId 
	 * @param requestDto-NotNull
	 * @return ResponseDto
	 */
	
	@Override
	public ResponseDto requestBook(BarrowedReqDto requestDto) {
		logger.info("UserServiceImpl::----------requestBook()");
		RequestedBook request=new RequestedBook();
		BeanUtils.copyProperties(requestDto, request);
		requestedBookRepository.save(request);
		return ResponseDto.builder().message(Constants.REQUESTED_BOOK)
				.statusCode(Constants.CREATED).build();
		
	}

}
