package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.entities.*;
import com.example.demo.service.BookService;
import com.example.demo.utils.RequestErrorHandler;

import jakarta.validation.Valid;

import com.example.demo.models.dtos.*;

@Controller
public class LibraryController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private RequestErrorHandler errorHandler;
        
    @PostMapping("/save")
    public String saveBook(@ModelAttribute @Valid SaveBookDTO bookInfo,
    		BindingResult validations, Model model) {
    	
    	String time = Calendar.getInstance().getTime().toString();
    	model.addAttribute("time", time);
    	
    	if (validations.hasErrors()) {
    		System.out.println("Hay errores");
    		
    		// Show errors in console
    		List<FieldError> errors = validations.getFieldErrors();
    		errors.forEach((error) -> {
    			System.out.println(error.getField() + ": " + error.getDefaultMessage());
    		});
    		
    		if (!errors.isEmpty())
    			model.addAttribute("hasErrors", true);
    		
    		model.addAllAttributes(errorHandler.mapErrors(errors));
    		
    		return "main";
    	}
    	
    	System.out.println(bookInfo);
    	
    	Book newBook = new Book(bookInfo.getIsbn(), bookInfo.getTitle());
    	// library.add(newBook);
    	bookService.save(newBook);
    	
    	return "redirect:/all";
    }
    
    @PostMapping("/update")
    public String updateBook(@ModelAttribute SaveBookDTO bookInfo) {
    	
    	// Book book = library.stream().filter(b -> b.getIsbn().equals(bookInfo.getIsbn())).findFirst().orElse(null);
    	Book book = bookService.findOneById(bookInfo.getIsbn());
    	
    	if (book != null) {
    		book.setTitle(bookInfo.getTitle());
    		System.out.println(book);
    	}
    	
    	return "redirect:/all";
    }
    
    @GetMapping("/")
    public String getMainPage(Model model) {
    	String time = Calendar.getInstance().getTime().toString();
    	model.addAttribute("time", time);
    	
    	return "main";    
    }
    
    @GetMapping("/all")
    public String getBookList(Model model) {
    	model.addAttribute("books", bookService.findAll());
    	return "book-list";
    }
    
    @GetMapping("/book/{isbn}")
    public String getUpdatePage(@PathVariable String isbn, Model model) {
    	String time = Calendar.getInstance().getTime().toString();
    	model.addAttribute("time", time);
    	
    	Book defaultBook = new Book("843760494X", "Cien Años de Soledad");
    	// Book book = library.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(defaultBook);
    	Book book = bookService.findOneById(isbn);
    	
    	model.addAttribute("bookTitle", book.getTitle());
    	
    	return "update-book";    
    }
    
}
