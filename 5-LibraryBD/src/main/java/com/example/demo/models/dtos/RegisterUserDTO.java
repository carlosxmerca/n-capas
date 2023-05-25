package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDTO {
	@NotEmpty(message = "username is required")
	private String username;
	
	@NotEmpty(message = "email is required")
	private String email;
	
	@NotEmpty(message = "password is required")
	private String password;

	@NotEmpty(message = "name is required")
	private String name;
}
