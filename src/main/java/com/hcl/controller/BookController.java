package com.hcl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.hcl.service.BookService;

/**
 * 
 * @author Sravya U
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
	public List<BookResponseDTO> searchBooks(@PathVariable String searchVariable) {
		LOGGER.info("Enter into BookController::-----searchBooks()");
		return bookService.searchBooks(searchVariable);

	}
	
	/**
	 * 
	 * @param AddBookDTO
	 * @return AddBookResponse
	 */
	@PostMapping("/")
	public AddBookResponse addBook(@RequestBody AddBookDTO addBookDTO) {
		LOGGER.info("Enter into BookController::-----addBooks()");
		return bookService.addBooks(addBookDTO);

	}

}
