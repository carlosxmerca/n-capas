package com.example.demo.services;

import com.example.demo.models.dto.RegisterDTO;
import com.example.demo.models.entities.User;

public interface UserService {
	void register(RegisterDTO info);
	User findOneById(String id);
}
