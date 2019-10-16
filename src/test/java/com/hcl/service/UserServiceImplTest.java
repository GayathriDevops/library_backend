package com.hcl.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.dto.BarrowedReqDto;
import com.hcl.dto.LoginReqDto;
import com.hcl.dto.LoginResDto;
import com.hcl.dto.ResponseDto;
import com.hcl.entity.BorrowedBook;
import com.hcl.entity.User;
import com.hcl.repository.BorrowedBookRepository;
import com.hcl.repository.RequestedBookRepository;
import com.hcl.repository.UserRepository;
import com.hcl.util.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Mock
	private RequestedBookRepository requestedBookRepository;
	@Mock
	private BorrowedBookRepository barrowedBookRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private LoginReqDto loginReq;
	private LoginResDto loginRes;
	private User user;
	private ResponseDto response;
	private BorrowedBook borrowedBook;
	private BarrowedReqDto barrowedReqDto;

	@Before
	public void setUp() {
		user = new User();
		user.setUserId(1L);
		user.setEmail("Pradeep.aj28@gmail.com");
		user.setPassword("Pradeep");
		barrowedReqDto = BarrowedReqDto.builder().userId(1L).bookId(1L).endDate(LocalDate.now())
				.fromDate(LocalDate.now()).build();
		borrowedBook = new BorrowedBook();
		BeanUtils.copyProperties(barrowedReqDto, borrowedBook);
		borrowedBook.setBId(1L);

		loginReq = LoginReqDto.builder().email("Pradeep.aj28@gmail.com").password("Pradeep").build();
		loginRes = LoginResDto.builder().message(Constants.LOG_SUCCESS_MESSAGE).statusCode(Constants.OK).userId(1L)
				.build();
		response = ResponseDto.builder().message("Login Success").statusCode(200).build();
	}

	/*
	 * @Test public void loginTest() {
	 * 
	 * Mockito.when(userRepository.findByEmail(loginReq.getEmail())).thenReturn(
	 * Optional.of(user)); LoginResDto actualValue =
	 * userServiceImpl.login(loginReq); assertEquals(loginRes.getStatusCode(),
	 * actualValue.getStatusCode()); }
	 * 
	 * @Test public void borrowBookTest() {
	 * Mockito.when(barrowedBookRepository.save(borrowedBook)).thenReturn(
	 * borrowedBook); ResponseDto actRes =
	 * userServiceImpl.barrowBook(barrowedReqDto);
	 * assertEquals(response.getStatusCode(), actRes.getStatusCode()); }
	 */
}
