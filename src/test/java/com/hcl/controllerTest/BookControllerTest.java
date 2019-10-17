package com.hcl.controllerTest;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hcl.controller.BookController;
import com.hcl.dto.AddBookDTO;
import com.hcl.dto.AddBookResponse;
import com.hcl.dto.BookResponseDTO;
import com.hcl.dto.SearchResponse;
import com.hcl.entity.Book;
import com.hcl.service.BookServiceImpl;
import com.hcl.util.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookControllerTest {

	@Mock
	BookServiceImpl bookServiceImpl;
	
	@InjectMocks
	BookController bookController;

	@Test
	public void addBookTest() {
		List<BookResponseDTO> response = new ArrayList<BookResponseDTO>();
		BookResponseDTO res = new BookResponseDTO();

		AddBookDTO addBook = new AddBookDTO();
		addBook.setAuthorName("abc");
		addBook.setBookName("ab");
		addBook.setGenre("fiction");
			
		Book book = new Book();
		book.setAuthorName("abc");
		book.setBookName("ab");
		book.setGenre("fiction");
		book.setRating(4.5F);
		book.setBookStatus(Constants.BOOK_AVAILABLE);

		AddBookResponse response1 = new AddBookResponse();
		response1.setStatusCode(201);
		response1.setStatusMessage("Success");

		res.setAuthorName("Ramkumar");
		res.setBookId(1L);
		res.setBookName("Hibernate");
		res.setBookStatus("Avaliable");
		res.setGenre("Technical");
		res.setRating(200f);
		response.add(res);
		
		Mockito.when(bookServiceImpl.addBooks(Mockito.any())).thenReturn(book);
		
		ResponseEntity<AddBookResponse> addBook2 = bookController.addBook(addBook);

		assertEquals(response1.getStatusCode(),addBook2.getBody().getStatusCode());
	}
	
	
	@Test
	public void searchBookTest() {
		
		BookResponseDTO response = new BookResponseDTO();
		response.setAuthorName("Ramkumar");
		response.setBookId(1L);
		response.setBookName("Hibernate");
		response.setBookStatus("Avaliable");
		response.setGenre("Technical");
		response.setRating(4.5F);
		
		List<BookResponseDTO> responseList = new ArrayList<>();
		responseList.add(response);
		
		SearchResponse response1 = new SearchResponse();
		response1.setBookResponse(responseList);
		
		Mockito.when(bookServiceImpl.searchBooks(Mockito.anyString())).thenReturn(responseList);
		
		ResponseEntity<SearchResponse> searchBook = bookController.searchBooks(Mockito.anyString());

		assertEquals(response1.getBookResponse().get(0).getBookName(),searchBook.getBody().getBookResponse().get(0).getBookName());

	}
}