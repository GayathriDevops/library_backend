package com.hcl.config;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hcl.entity.Book;
import com.hcl.entity.BorrowedBook;
import com.hcl.exception.BookNotPresentException;
import com.hcl.repository.BookRepository;
import com.hcl.repository.BorrowedBookRepository;
import com.hcl.util.Constants;

/**
 * 
 * @author Sravya U
 *
 */
@Service
public class LibraryScheduler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryScheduler.class);


	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BorrowedBookRepository borrowedBookRepository;
	
	//@Scheduled(cron= "0 0 1 * * *")
	@Scheduled(cron = "0 */2 * * * *")
	public void performTaskUsingCron() {
		
		LOGGER.info("performTaskUsingCron");
		LOGGER.info("Current Time      = {}",LocalDate.now());

		
		LocalDate now = LocalDate.now();		
		List<BorrowedBook> borrowed = borrowedBookRepository.getBorrowedBook(now);

		borrowed.forEach(borrowedBook->{
			LOGGER.info("borrowed book list----={}",borrowedBook);
			borrowedBookRepository.delete(borrowedBook);
			Optional<Book> books = bookRepository.findByBookId(borrowedBook.getBookId());
		if(books.isPresent()) {
			Book b=books.get();
			b.setBookStatus(Constants.BOOK_AVAILABLE);
			bookRepository.save(b);
		} else {
			throw new BookNotPresentException(Constants.NO_BOOKS);
		}
		});
	}

}
