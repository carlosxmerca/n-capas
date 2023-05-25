package com.example.demo.models.dtos.songs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveSongDTO {
	@NotBlank(message = "name is required")
	private String name;
	
	@NotBlank(message = "duration is required")
	private int duration;
}
