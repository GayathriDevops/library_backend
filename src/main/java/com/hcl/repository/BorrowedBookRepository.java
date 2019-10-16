package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.entity.BorrowedBook;
@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {

}
