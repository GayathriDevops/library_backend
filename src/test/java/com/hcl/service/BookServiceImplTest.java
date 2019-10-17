package com.hcl.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.hcl.dto.AddBookDTO;
import com.hcl.dto.BookResponseDTO;
import com.hcl.entity.Book;
import com.hcl.exception.BookNotPresentException;
import com.hcl.repository.BookRepository;
import com.hcl.util.Constants;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookServiceImplTest {

	@Mock
	BookRepository bookRepository;

	@InjectMocks
	BookServiceImpl BookServiceImpl;

	@Test
	public void testAddBook() {

		AddBookDTO addBookDTO = new AddBookDTO();
		addBookDTO.setAuthorName("abc");
		addBookDTO.setBookName("ab");
		addBookDTO.setGenre("fiction");
		
		Book book = new Book();
		book.setAuthorName("abc");
		book.setBookName("ab");
		book.setGenre("fiction");
		book.setRating(4.5F);
		book.setBookStatus(Constants.BOOK_AVAILABLE);

		Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

		Book result = BookServiceImpl.addBooks(addBookDTO);
		assertNotNull(result);
		assertEquals("ab", result.getBookName());
	}

	@Test
	public void searchBooks() {

		BookResponseDTO bookResponseDTO = new BookResponseDTO();
		bookResponseDTO.setBookName("harry");
		bookResponseDTO.setBookId(1L);
		bookResponseDTO.setAuthorName("jk");
		bookResponseDTO.setBookStatus("avail");
		bookResponseDTO.setGenre("fiction");
		bookResponseDTO.setRating(4.5F);

		Book book = new Book();
		BeanUtils.copyProperties(bookResponseDTO, book);

		List<Book> list = new ArrayList<>();
		list.add(book);

		Optional<List<Book>> optList = Optional.of(list);

		List<BookResponseDTO> books = new ArrayList<BookResponseDTO>();
		books.add(bookResponseDTO);

		Mockito.when(bookRepository.findByBookNameOrAuthorName(Mockito.anyString())).thenReturn(optList);

		List<BookResponseDTO> searchBooks = BookServiceImpl.searchBooks(Mockito.anyString());
		assertNotNull(searchBooks);
		assertEquals(bookResponseDTO.getBookName(), searchBooks.get(0).getBookName());

	}
	
	@Test(expected = BookNotPresentException.class)

	public void BookNotPresentExceptionTest() {

		Mockito.when(bookRepository.findByBookNameOrAuthorName(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

		List<BookResponseDTO> searchBooks = BookServiceImpl.searchBooks(Mockito.anyString());
		assertNotNull(searchBooks);
		assertEquals(0, searchBooks.size());

	}
}
