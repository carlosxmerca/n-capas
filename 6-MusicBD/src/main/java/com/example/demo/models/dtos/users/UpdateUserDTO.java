package com.example.demo.models.dtos.users;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDTO {
	@NotBlank(message = "code is required")
	private UUID code;
	
	@NotBlank(message = "username is required")
	private String username;
	
	@NotBlank(message = "email is required")
	private String email;
}
