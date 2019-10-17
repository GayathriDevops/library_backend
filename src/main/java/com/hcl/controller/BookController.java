package com.hcl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.AddBookDTO;
import com.hcl.dto.AddBookResponse;
import com.hcl.dto.BookResponseDTO;
import com.hcl.dto.SearchResponse;
import com.hcl.service.BookService;
import com.hcl.util.Constants;

/**
 * 
 * @author Sravya U
 *
 */

/**
 * 
 * @apiNote- search books
 * 			 add books
 *
 */
@RestController
@RequestMapping("/books")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class BookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	/**
	 * 
	 * @param searchVariable-String
	 * @return List<BookResponseDTO>
	 */
	@GetMapping("/{searchVariable}")
	public ResponseEntity<SearchResponse> searchBooks(@PathVariable String searchVariable) {
		
		LOGGER.info("Enter into BookController::-----searchBooks()");
		
		List<BookResponseDTO> searchBooks = bookService.searchBooks(searchVariable);
		SearchResponse response = new SearchResponse();
		response.setBookResponse(searchBooks);
		
		return new ResponseEntity<>(response,HttpStatus.OK);

	}
	
	/**
	 * 
	 * @param AddBookDTO
	 * @return AddBookResponse
	 */
	@PostMapping("/")
	public ResponseEntity<AddBookResponse> addBook(@RequestBody AddBookDTO addBookDTO) {
		LOGGER.info("Enter into BookController::-----addBooks()");
		
		bookService.addBooks(addBookDTO);
		
		AddBookResponse response = new AddBookResponse();
		response.setStatusCode(Constants.CREATED);
		response.setStatusMessage(Constants.ADD_BOOK);

		return new ResponseEntity<>(response,HttpStatus.CREATED);

	}

}
