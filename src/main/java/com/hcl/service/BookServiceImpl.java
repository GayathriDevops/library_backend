package com.hcl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.AddBookDTO;
import com.hcl.dto.BookResponseDTO;
import com.hcl.entity.Book;
import com.hcl.exception.BookNotPresentException;
import com.hcl.repository.BookRepository;
import com.hcl.util.Constants;


/**
 * 
 * @author Sravya U
 *
 */
@Service
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookRepository bookRepository;

	/**
	 * 
	 * @param searchVariable-String
	 * @return List<BookResponseDTO>
	 */
	@Override
	public List<BookResponseDTO> searchBooks(String searchVariable) {

		LOGGER.info("searchBooks()----{}",searchVariable);

		Optional<List<Book>> bookList = bookRepository.findByBookNameOrAuthorName(searchVariable);

		if (!bookList.isPresent())
			throw new BookNotPresentException(Constants.NO_BOOKS);

		List<Book> books = bookList.get();

		List<BookResponseDTO> booksList = new ArrayList<>();

		books.forEach(book -> {
			BookResponseDTO booksres = new BookResponseDTO();
			BeanUtils.copyProperties(book, booksres);
			booksList.add(booksres);
		});

		return booksList;
	}
	
	
	/**
	 * 
	 * @param AddBookDTO
	 * @return AddBookResponse
	 */
	@Override
	public Book addBooks(AddBookDTO addBookDTO) {
		
		LOGGER.info("addBooks()");
		
		Book book = new Book();
		book.setAuthorName(addBookDTO.getAuthorName());
		book.setBookName(addBookDTO.getBookName());
		book.setGenre(addBookDTO.getGenre());
		book.setRating(4.5F);
		book.setBookStatus(Constants.BOOK_AVAILABLE);
		return bookRepository.save(book);
	
	}
}
