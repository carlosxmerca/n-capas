package com.example.demo.models.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDTO {
	@NotBlank(message = "username is required")
	private String username;
	
	@NotBlank(message = "email is required")
	@Email(message = "email must be valid")
	private String email;
	
	@NotBlank(message = "password is required")
	@Size(min = 5, message = "Name size is 5 chars")
	private String password;
}
