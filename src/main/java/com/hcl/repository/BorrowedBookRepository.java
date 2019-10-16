package com.hcl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.hcl.entity.BorrowedBook;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Long> {
	
	@Query(value="select book_id from borrowed_book where user_id=?",nativeQuery=true)
    public List<Long> getBookByUserId(Long userId);
	
	@Query(value="select * from borrowed_book where book_id=?",nativeQuery=true)
	public BorrowedBook getBookDetails(Long bookId);

}
