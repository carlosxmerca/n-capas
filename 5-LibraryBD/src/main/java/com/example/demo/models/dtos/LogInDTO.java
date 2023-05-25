package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogInDTO {
	@NotBlank(message = "required identification")
	private String id;
	@NotBlank(message = "required password")
	private String password;
}
