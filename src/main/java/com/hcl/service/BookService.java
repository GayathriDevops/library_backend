package com.hcl.service;

import java.util.List;
import com.hcl.dto.AddBookDTO;
import com.hcl.dto.AddBookResponse;
import com.hcl.dto.BookResponseDTO;

public interface BookService {

	List<BookResponseDTO> searchBooks(String searchVariable);

	AddBookResponse addBooks(AddBookDTO addBookDTO);

}
