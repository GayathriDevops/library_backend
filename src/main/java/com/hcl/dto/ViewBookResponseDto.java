package com.hcl.dto;

import java.time.LocalDate;
import java.util.List;

import com.hcl.dto.BookDetails.BookDetailsBuilder;

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
public class ViewBookResponseDto {
	private List<BookDetails> bookDetails;	
}
