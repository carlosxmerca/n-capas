package com.example.demo.models.dtos.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordDTO {
	@NotBlank(message = "id is required")
	private String id;

	@NotBlank(message = "old password is required")
	@Size(min = 5, message = "password size is 5 chars")
	private String password;
	
	@NotBlank(message = "new password is required")
	@Size(min = 5, message = "password size is 5 chars")
	private String newPassword;
}
