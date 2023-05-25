package com.example.demo.models.dtos.users;

import jakarta.validation.constraints.NotBlank;

public class ChangePasswordDTO {
	@NotBlank(message = "old password is required")
	private String password;
	
	@NotBlank(message = "new password is required")
	private String newPassword;
}
