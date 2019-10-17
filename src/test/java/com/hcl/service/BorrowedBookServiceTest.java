package com.hcl.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.entity.BorrowedBook;
import com.hcl.repository.BorrowedBookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowedBookServiceTest {
	
	@MockBean
	private BorrowedBookRepository borrowedBookRepository;
	
	
	@Test
	public void getBookByUserId() {
		List<BorrowedBook> books= new ArrayList<BorrowedBook>();
		
		BorrowedBook b= new BorrowedBook();
		b.setBId(1L);
		b.setBookId(1L);
		b.setEndDate(LocalDate.now());
		b.setFromDate(LocalDate.now());
		b.setUserId(1L);
		books.add(b);
		when(borrowedBookRepository.getBookDetails(1L)).thenReturn(b);
		assertEquals(b.getBId(), borrowedBookRepository.getBookDetails(1L).getBId());
	}
}
