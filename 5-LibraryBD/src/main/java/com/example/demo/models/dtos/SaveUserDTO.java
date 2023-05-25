package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveUserDTO {
	@NotBlank(message = "username is required")
	private String username;
	
	@NotBlank(message = "email is required")
	private String email;
	
	@NotBlank(message = "password is required")
	private String password;
	
	@NotBlank(message = "name is required")
	private String name;
}
