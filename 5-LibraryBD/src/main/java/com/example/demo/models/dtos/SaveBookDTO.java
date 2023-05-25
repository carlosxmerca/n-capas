package com.example.demo.models.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveBookDTO {
	@NotEmpty
	private String isbn;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private Date publishDate;
	
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Z]{4}$")
	private String category;
}
