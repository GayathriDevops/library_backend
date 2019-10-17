package com.hcl.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "requestedBook")
public class RequestedBook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rId;
	private Long userId;
	private Long bookId;
	private LocalDate fromDate;
	private LocalDate endDate;
}
