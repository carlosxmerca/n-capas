package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordDTO {
	@NotEmpty(message = "identifier is required")
	private String id;
	
	@NotEmpty(message = "old password is required")
	private String password;
	
	@NotEmpty(message = "new password is required")
	private String newPassword;
}
