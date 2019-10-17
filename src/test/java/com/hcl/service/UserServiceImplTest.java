package com.hcl.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import java.util.Optional;

import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.BeanUtils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

@RunWith(MockitoJUnitRunner.Silent.class)

public class UserServiceImplTest {

	@Mock

	private UserRepository userRepository;

	@Mock

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Mock

	private RequestedBookRepository requestedBookRepository;

	@Mock

	private BorrowedBookRepository barrowedBookRepository;

	@Mock

	BookRepository bookRepository;

	@InjectMocks

	private UserServiceImpl userServiceImpl;

	private LoginReqDto loginReq;

	private LoginResDto loginRes;

	private RegisterReqDto registerReqDto;

	private RegisterReqDto registerReqDto1;

	private User user;

	private User newUser;

	private BorrowedBook borrowedBook;

	private BarrowedReqDto barrowedReqDto;

	private BarrowedReqDto barrowedReqDto1;

	private RequestedBook requestedBook;

	private Book book;

	@Before

	public void setUp() {

		MockitoAnnotations.initMocks(this);

		book = new Book();

		book.setBookId(1L);

		book.setBookStatus("borrowed");

		user = new User();

		newUser = new User();

		user.setUserId(1L);

		user.setEmail("Pradeep.aj28@gmail.com");

		user.setPassword(bCryptPasswordEncoder.encode("Pradeep"));

		newUser.setUserId(2L);

		newUser.setEmail("Pradeep.ajjj28@gmail.com");

		newUser.setPassword(bCryptPasswordEncoder.encode("Pradeep"));

		barrowedReqDto = BarrowedReqDto.builder().userId(1L).bookId(1L).endDate(LocalDate.now())

				.fromDate(LocalDate.now()).build();

		borrowedBook = new BorrowedBook();

		BeanUtils.copyProperties(barrowedReqDto, borrowedBook);

		borrowedBook.setBId(1L);

		loginReq = LoginReqDto.builder().email("Pradeep.aj28@gmail.com").password("Pradeep").build();

		loginRes = LoginResDto.builder().message(Constants.LOG_SUCCESS_MESSAGE).statusCode(Constants.OK).userId(1L)

				.build();

		registerReqDto = RegisterReqDto.builder().build();

		registerReqDto1 = RegisterReqDto.builder().userName("Pradeep").email("Pradeep.aj28@gmail.com")

				.password("Pradeep").adarId("1234qwe").address("blr").phone("234567").build();

		barrowedReqDto1 = BarrowedReqDto.builder().userId(1L).bookId(2L).endDate(LocalDate.now())

				.fromDate(LocalDate.now()).build();

	}

	@Test

	public void loginTest() {

		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

		Mockito.when(bCryptPasswordEncoder.matches(loginReq.getPassword(), user.getPassword())).thenReturn(true);

		LoginResDto actualValue = userServiceImpl.login(loginReq);

		assertEquals(loginRes.getStatusCode(), actualValue.getStatusCode());

	}

	@Test(expected = InvalidCredentialsException.class)

	public void InvalidCredentialsExceptionTest() {

		userServiceImpl.login(loginReq);

	}

	@Test

	public void borrowBookTest() {

		Mockito.when(barrowedBookRepository.save(borrowedBook)).thenReturn(borrowedBook);

		Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));

		Mockito.when(bookRepository.save(book)).thenReturn(book);

		ResponseDto actRes = userServiceImpl.barrowBook(barrowedReqDto);

		assertEquals(201, actRes.getStatusCode());

	}

	@Test(expected = BookNotPresentException.class)

	public void BookNotPresentExceptionTest() {

		userServiceImpl.barrowBook(barrowedReqDto1);

	}

	@Test

	public void reuestBookTest() {

		Mockito.when(requestedBookRepository.save(requestedBook)).thenReturn(requestedBook);

		ResponseDto actRes = userServiceImpl.requestBook(barrowedReqDto);

		assertEquals(201, actRes.getStatusCode());

	}

	@Test

	public void createNewUserTest() {

		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

		Mockito.when(userRepository.save(newUser)).thenReturn(newUser);

		ResponseDto actRes = userServiceImpl.createNewUser(registerReqDto);

		assertEquals(201, actRes.getStatusCode());

	}

	@Test(expected = UserExitsException.class)

	public void UserExitsExceptionTest() {

		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));

		userServiceImpl.createNewUser(registerReqDto1);

	}

}