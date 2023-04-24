package com.example.demo.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dto.BookDTO;
import com.example.demo.models.entities.Book;
import com.example.demo.services.BookService;
import com.example.demo.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private RequestErrorHandler errorHandler;
	private static DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
	
	@GetMapping("")
	public ResponseEntity<?> getBooks() {
		return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBooks(@PathVariable String id) {
		Book book = bookService.findOneById(id);
		
		if (book == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveBook(@Valid BookDTO info, BindingResult validations) {
		
		if (validations.hasErrors())
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
		
		try {
			Book newBook = new Book(info.getIsbn(), info.getTitle(), date.parse(info.getPublishDate()), info.getUrl()); 
			bookService.save(newBook);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable String id) {
		Book book = bookService.findOneById(id);
		
		if (book == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		bookService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
