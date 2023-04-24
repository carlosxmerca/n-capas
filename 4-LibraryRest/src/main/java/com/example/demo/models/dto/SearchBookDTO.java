package com.example.demo.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchBookDTO {
	@NotEmpty(message = "Isbn required")
	@Size(min = 10, max = 10, message = "Isbn must be 10 characters")
	private String isbn;
	
	@NotEmpty(message = "Identifier required")
	private String identifier;
}
