package com.hcl.service;

import java.util.ArrayList;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.BookDetails;
import com.hcl.dto.ViewBookResponseDto;
import com.hcl.entity.Book;
import com.hcl.entity.BorrowedBook;
import com.hcl.exception.DataNotFoundException;
import com.hcl.repository.BookRepository;
import com.hcl.repository.BorrowedBookRepository;

@Service
public class BorrowedBookServiceImpl  implements BorrowedBookService{
	
	private static Logger logger = LoggerFactory.getLogger(BorrowedBookServiceImpl.class);
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BorrowedBookRepository borrowedBookRepository;
	@Override
	public ViewBookResponseDto getBookByUserId(Long request) {	
		
   logger.info("getBookByUserId() in  BorrowedBookServiceImpl started");
	ViewBookResponseDto response= new ViewBookResponseDto();
    List<Long> borrowedBookId=borrowedBookRepository.getBookByUserId(request);
	List<BookDetails> detailList= new ArrayList<>();
	
	if(!borrowedBookId.isEmpty())
	{
		borrowedBookId.stream().forEach(bookId -> {
			BookDetails details= new BookDetails();
			Book bs=bookRepository.findById(bookId).get();
			BorrowedBook books=borrowedBookRepository.getBookDetails(bookId);
			details.setAuthorName(bs.getAuthorName());
			details.setBookId(bs.getBookId());
			details.setBookName(bs.getBookName());
			details.setBookStatus(bs.getBookStatus());
			details.setGenre(bs.getGenre());
			details.setRating(bs.getRating());
			details.setFromDate( books.getFromDate());
			details.setEndDate(books.getEndDate());
			detailList.add(details);
			response.setBookDetails(detailList);
			});
		
	}else
	{
		throw new DataNotFoundException("No Book Borrowed From Library...");
	}
	
		return response;
	}

}
