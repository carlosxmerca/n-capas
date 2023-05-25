package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.ChangePasswordDTO;
import com.example.demo.models.dtos.LogInDTO;
import com.example.demo.models.dtos.RegisterUserDTO;
import com.example.demo.models.dtos.UpdateUserDTO;
import com.example.demo.models.entities.User;
import com.example.demo.services.AuthServices;
import com.example.demo.services.UserServices;
import com.example.demo.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
	
	// 1. registrarse
	// 2. ruta que logee, usuario o correo, contrasena
	// 3. dado un usuario permita modificar el nombre
	// 4. cambiar contrasena
	// TODO: 5. añadir campo active y hacer el toggle
		
	@Autowired
	private AuthServices authService;
	@Autowired
	private UserServices userService;
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@PostMapping("/log-in")
	private ResponseEntity<?> logIn(@ModelAttribute @Valid LogInDTO data, BindingResult validations) {
		
		if (validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		}
		
		User user = authService.logIn(data);
		
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>("logged in", HttpStatus.OK);
	}
	
	@PostMapping("")
	private ResponseEntity<?> register(@ModelAttribute RegisterUserDTO data) {
		try {
			userService.register(data);
			return new ResponseEntity<>("user created", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
		
	@PutMapping("")
	private ResponseEntity<?> udpateUserName(@ModelAttribute UpdateUserDTO data) {
		try {
			User user = userService.findOneById(data.getId());
			
			if (user == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			userService.update(data);
			return new ResponseEntity<>("user updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PatchMapping("/change-password")
	private ResponseEntity<?> changePassword(@ModelAttribute ChangePasswordDTO data) {
		try {
			User user = userService.findOneById(data.getId());
			if (user == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			// TODO: notify if current password is incorrect
			authService.changePassword(data);
			return new ResponseEntity<>("password changed", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
}
