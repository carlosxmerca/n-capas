package com.example.demo.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.users.RegisterUserDTO;
import com.example.demo.models.dtos.users.UpdateUserDTO;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(RegisterUserDTO userInfo) throws Exception {
		User user = new User(
				userInfo.getUsername(),
				userInfo.getEmail(),
				userInfo.getPassword() // TODO: encrypt password
				);
		
		userRepository.save(user);
		
	}

	@Override
	public void update(UpdateUserDTO userInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String code) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findOneById(String code) {
		return userRepository.findOneByUsernameOrEmail(code, code);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
