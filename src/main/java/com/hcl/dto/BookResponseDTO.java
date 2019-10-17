package com.hcl.dto;

import lombok.Data;

@Data
public class BookResponseDTO {

	private Long bookId;
	private String bookName;
	private String authorName;
	private String genre;
	private Float rating;
	private String bookStatus;
	

}
