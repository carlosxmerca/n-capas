package com.example.demo.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.ChangePasswordDTO;
import com.example.demo.models.dtos.LogInDTO;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AuthServices;

@Service
public class AuthServiceImpl implements AuthServices {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User logIn(LogInDTO data) {
		User user = userRepository.findOneByUsernameOrEmail(data.getId(), data.getId());
		
		if (user == null) return null;
		if (!user.getPassword().equals(data.getPassword())) return null;
		
		return user;
	}

	@Override
	public void changePassword(ChangePasswordDTO data) throws Exception {
		User user = userRepository.findOneByUsernameOrEmail(data.getId(), data.getId());
		
		if (user == null) return;
		if (!user.getPassword().equals(data.getPassword())) return;
		
		user.setPassword(data.getNewPassword());
		userRepository.save(user);
	}
	
}
