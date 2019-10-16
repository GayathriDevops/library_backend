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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.BookDetails;
import com.hcl.dto.ViewBookResponseDto;
import com.hcl.service.BorrowedBookService;

@RestController
@RequestMapping("/book")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class BookController {
	
    private static Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	BorrowedBookService borrowedBookService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<ViewBookResponseDto> getBookByUserId(@PathVariable("userId") Long request)
	{
		logger.info("getBookByUserId() in  BookController started");
		return new ResponseEntity<>(borrowedBookService.getBookByUserId(request),HttpStatus.OK);
	}

}
