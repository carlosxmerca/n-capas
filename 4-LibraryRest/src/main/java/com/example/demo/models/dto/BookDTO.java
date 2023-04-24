package com.example.demo.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
	@NotNull(message = "isbn required")
	@Size(min = 10, max = 10, message = "Isbn must be 10 characters")
	private String isbn;
	
	@NotNull(message = "title required")
	private String title;
	
	@NotNull(message = "publish date required")
	private String publishDate;
	
	@NotNull(message = "url required")
	@NotEmpty(message = "url must not be empty")
	private String url;
}
