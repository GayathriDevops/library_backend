package com.hcl.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hcl.entity.BorrowedBook;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {

	@Query("select b from BorrowedBook b where b.endDate =:now")
	List<BorrowedBook> getBorrowedBook(@Param("now") LocalDate now);

}
