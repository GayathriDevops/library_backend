package com.hcl.dto;

import java.util.List;

import lombok.Data;

@Data
public class SearchResponse {
	
	List<BookResponseDTO> bookResponse;
	
}
