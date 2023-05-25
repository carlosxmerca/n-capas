package com.example.demo.models.dtos.songs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSongDTO {
	@NotBlank(message = "code is required")
	private String code;
	
	@NotBlank(message = "name is required")
	private String name;
	
	@NotBlank(message = "duration is required")
	private int duration;
}
