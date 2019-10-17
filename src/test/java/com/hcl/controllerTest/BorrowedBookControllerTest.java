package com.hcl.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hcl.controller.ViewBookController;
import com.hcl.dto.BookDetails;
import com.hcl.dto.ViewBookResponseDto;
import com.hcl.service.BorrowedBookServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class BorrowedBookControllerTest {

	
	@InjectMocks
	ViewBookController bookController;
	
	@Mock
	BorrowedBookServiceImpl borrowBookService;
	
	@Test
	public void getAllBeneficiaryControllerTest()
	{
	   ViewBookResponseDto response= new ViewBookResponseDto();
	   List<BookDetails> details= new ArrayList<BookDetails>();
	   BookDetails book= new BookDetails();
	   book.setAuthorName("Vinayak");
	   book.setBookId(1L);
	   book.setBookName("AdvancedJava");
	   book.setBookStatus("Avaliable");
	   book.setEndDate(LocalDate.now());
	   book.setFromDate(LocalDate.now());
	   book.setGenre("Technical");
	   book.setRating(200f);
	   details.add(book);
	   response.setBookDetails(details);
		
		 when(borrowBookService.getBookByUserId(1L)).thenReturn(response);
		 assertEquals(1,bookController.getBookByUserId(1L).getBody().getBookDetails().size());
	}
	
	
}
