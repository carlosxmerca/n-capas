package com.example.demo.models.dtos.playlists;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePlayListDTO {
	@NotBlank(message = "playlist code is required")
	private String code;
	
	@NotBlank(message = "title is required")
	private String title;
	
	@NotBlank(message = "description is required")
	private String description;

}
