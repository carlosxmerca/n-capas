package com.example.demo.services;

import java.util.List;

import com.example.demo.models.dtos.RegisterUserDTO;
import com.example.demo.models.dtos.UpdateUserDTO;
import com.example.demo.models.entities.User;

public interface UserServices {
	List<User> findAll();
	User findOneById(String id);
	void register(RegisterUserDTO data) throws Exception;
	void update(UpdateUserDTO data) throws Exception;
	
	Boolean comparePassword(String toCompare, String actual);
}
