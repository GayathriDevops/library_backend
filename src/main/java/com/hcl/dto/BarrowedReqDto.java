package com.hcl.dto;


/**
 * 
 * @author Pradeepa AJ
 *
 */
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class BarrowedReqDto {
	private Long userId;
	private Long bookId;
	private LocalDate fromDate;
	private LocalDate endDate;
}
