package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookLoanDTO {
	@NotBlank(message = "book code required")
	private String bookCode;
	
	@NotBlank(message = "user code required")
	private String userCode;
}
