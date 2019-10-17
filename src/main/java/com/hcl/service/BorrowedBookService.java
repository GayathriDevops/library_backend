package com.hcl.service;

import com.hcl.dto.ViewBookResponseDto;


public interface BorrowedBookService {

	public ViewBookResponseDto getBookByUserId(Long request);
	
	
	
}
