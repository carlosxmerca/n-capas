package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.users.RegisterUserDTO;
import com.example.demo.models.dtos.users.UpdateUserDTO;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	
	@PostMapping("")
	public ResponseEntity<?> register(@ModelAttribute RegisterUserDTO data) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getUsers() {
		return new ResponseEntity<>("users", HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser() {
		return new ResponseEntity<>("user", HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateUser(@ModelAttribute UpdateUserDTO data) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("/change-password")
	public ResponseEntity<?> changePassword() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
