package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.entity.RequestedBook;
@Repository
public interface RequestedBookRepository extends JpaRepository<RequestedBook, Long> {

}
