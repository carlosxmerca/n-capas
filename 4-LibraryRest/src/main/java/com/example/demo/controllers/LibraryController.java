package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dto.BookUserDTO;
import com.example.demo.models.dto.SearchBookDTO;
import com.example.demo.models.entities.Book;
import com.example.demo.models.entities.User;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class LibraryController {
	
	@Autowired
	private UserService userServie;
	@Autowired
	private BookService bookService;
	
	@GetMapping("/book")
	public ResponseEntity<?> findOneBookByIsbnAndUser(
			@Valid SearchBookDTO search, BindingResult validations) {
		
		if (validations.hasErrors())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		User userFound = userServie.findOneById(search.getIdentifier());
		if (userFound == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Book bookFound = bookService.findOneById(search.getIsbn());
		if (bookFound == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		BookUserDTO response = new BookUserDTO(bookFound, userFound);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
