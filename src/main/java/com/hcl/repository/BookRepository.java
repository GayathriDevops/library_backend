package com.hcl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("select b from Book b where b.bookName like %:searchVariable% or b.authorName like %:searchVariable%")
	Optional<List<Book>> findByBookNameOrAuthorName(@Param("searchVariable") String searchVariable);

	Optional<Book> findByBookId(Long bookId);

}
