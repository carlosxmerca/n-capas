package com.example.demo.services;

import com.example.demo.models.dtos.ChangePasswordDTO;
import com.example.demo.models.dtos.LogInDTO;
import com.example.demo.models.entities.User;

public interface AuthServices {
	User logIn(LogInDTO data);
	void changePassword(ChangePasswordDTO data) throws Exception;
}
