package com.hcl.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDetails {
	
	private Long bookId;
	private String bookName;
	private String authorName;
	private String genre;
	private Float rating;
	private String bookStatus;
	private LocalDate fromDate;
	private LocalDate endDate;

	
	
	

}
