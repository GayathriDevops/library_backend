package com.hcl.service;

import java.util.List;
import com.hcl.dto.AddBookDTO;
import com.hcl.dto.BookResponseDTO;
import com.hcl.entity.Book;

public interface BookService {

	List<BookResponseDTO> searchBooks(String searchVariable);

	Book addBooks(AddBookDTO addBookDTO);

}
