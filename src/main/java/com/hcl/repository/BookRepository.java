package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
