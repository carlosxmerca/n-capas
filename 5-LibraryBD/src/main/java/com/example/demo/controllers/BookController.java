package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.BookLoanDTO;
import com.example.demo.models.dtos.SaveBookDTO;
import com.example.demo.models.entities.Book;
import com.example.demo.models.entities.BookLoan;
import com.example.demo.models.entities.Category;
import com.example.demo.models.entities.User;
import com.example.demo.services.BookLoansServices;
import com.example.demo.services.BookServices;
import com.example.demo.services.CategoryServices;
import com.example.demo.services.UserServices;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class BookController {
	
	/* TODO VALIDADO POR EL ACTIVE DEL USUARIO
	 * 
	 *  1. dado el code un libro devuelva todos los prestamos del mismo
	 *  2. usuario dado un user, devuelva todos los prestamos que ha hecho el usuario
	 *  (libro: code, titulo, isbn; user: username )
	 *  
	 *  Controlador para reservas
	 *  1. dado un id de usuario y un isbn cree una ruta que asigne el libro
	 *  	- verificar que el libro este disponible
	 *  	- sino devueelve codigo de error
	 *  
	 *  2. dado un codigo de libro verifique si hay una reserva de ese libro
	 *  3. dado el codigo de libro establesca que ese libro fue devuelto
	 *  
	 * */
	
	@Autowired
	private BookServices bookService;
	@Autowired
	private BookLoansServices bookLoanService;
	@Autowired
	private UserServices userService;
	@Autowired
	private CategoryServices categoryService;
	
	@GetMapping("/collection")
	private ResponseEntity<?> findAllBook() {
		List<Book> books = bookService.findAll();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@GetMapping("/collection/{cat}")
	private ResponseEntity<?> findAllBooksByCategory(@PathVariable String cat) {
		Category category = categoryService.findOneById(cat);
		
		if (category == null)
			return new ResponseEntity<>("No existe esa m...", HttpStatus.NOT_FOUND);
					
		List<Book> books = category.getBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@PostMapping("")
	private ResponseEntity<?> createBook(@ModelAttribute SaveBookDTO data) {
		Category category = categoryService.findOneById(data.getCategory());
		
		if (category == null)
			return new ResponseEntity<>("No existe esa m...", HttpStatus.NOT_FOUND);
		
		try {
			bookService.save(data, category);
			return new ResponseEntity<>("Libro creado", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/collection/isbn/{isbn}")
	private ResponseEntity<?> findAllBookByIsbn(@PathVariable String isbn) {
		List<Book> books = bookService.findAllByISBN(isbn);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
	
	@GetMapping("/loans/code/{code}")
	private ResponseEntity<?> findLoanByBook(@PathVariable String code) {
		Book book = bookService.findByCode(code);
		List<BookLoan> bookLoans = book.getBookLoans();
		
		BookLoan currentLoan = bookLoans.stream()
				.filter(loan -> loan.getReturnDate() == null)
				.findFirst()
				.orElse(null);
		
		return new ResponseEntity<>(currentLoan, HttpStatus.OK);
	}
	
	@GetMapping("/loans/collection/code/{code}")
	private ResponseEntity<?> findLoansByBook(@PathVariable String code) {
		Book book = bookService.findByCode(code);
		
		if (book == null)
			return new ResponseEntity<>("book not found", HttpStatus.NOT_FOUND);
		
		List<BookLoan> bookLoans = book.getBookLoans();		
		return new ResponseEntity<>(bookLoans, HttpStatus.OK);
	}
	
	@GetMapping("/loans/collection/user/{id}")
	private ResponseEntity<?> findLoansByUser(@PathVariable String id) {
		User user = userService.findOneById(id);
		
		if (user == null)
			return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
		
		// TODO: use response DTO
		
		List<BookLoan> bookLoans = user.getBookLoans();		
		return new ResponseEntity<>(bookLoans, HttpStatus.OK);
	}
	
	@PostMapping("/loans")
	private ResponseEntity<?> createBookLoan(@ModelAttribute BookLoanDTO data) {
		Book book = bookService.findByCode(data.getBookCode());
		User user = userService.findOneById(data.getUserCode());
		
		if (book == null || user == null)
			return new ResponseEntity<>("book not found", HttpStatus.NOT_FOUND);
		
		// TODO: validate book availability
		
		try {
			bookLoanService.createLoan(book, user);
			return new ResponseEntity<>("book loan created", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/loans/close-loan/{code}")
	private ResponseEntity<?> closeBookLoan(@PathVariable String code) {
		return new ResponseEntity<>("book loan closed", HttpStatus.OK);
	}
	
}
