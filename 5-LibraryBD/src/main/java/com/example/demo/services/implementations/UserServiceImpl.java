package com.example.demo.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.RegisterUserDTO;
import com.example.demo.models.dtos.UpdateUserDTO;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServices;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserServices {
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOneById(String id) {
		return userRepository.findOneByUsernameOrEmail(id, id);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void register(RegisterUserDTO data) throws Exception {
		User newUser = new User(
				data.getUsername(), 
				data.getEmail(), 
				passwordEncoder.encode(data.getPassword()), 
				data.getName());
		
		userRepository.save(newUser);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void update(UpdateUserDTO data) throws Exception {
		User user = userRepository.findOneByUsernameOrEmail(data.getId(), data.getId());
		
		if (user == null) return;
		
		user.setName(data.getName());
		userRepository.save(user);
	}

	@Override
	public Boolean comparePassword(String toCompare, String actual) {
		return passwordEncoder.matches(toCompare, actual);
	}

}
