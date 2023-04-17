package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.entities.*;

import jakarta.validation.Valid;

import com.example.demo.models.dtos.*;

@Controller
public class LibraryController {
	
	private static List<Book> library = new ArrayList<>();
    
    static {
        library.add(new Book("0261102303", "Lord of the Rings"));
        library.add(new Book("0007441428", "Game of Thrones"));
        library.add(new Book("0747581088", "Harry Potter and the Half-Blood Prince"));
        library.add(new Book("1401248195", "Watchmen"));
        library.add(new Book("030788743X", "Ready player one"));
        library.add(new Book("843760494X", "Cien Años de Soledad"));
        library.add(new Book("0553804367", "A Briefer History of Time!!"));
    }
        
    @PostMapping("/save")
    public String saveBook(@ModelAttribute @Valid SaveBookDTO bookInfo,
    		BindingResult validations, Model model) {
    	
    	String time = Calendar.getInstance().getTime().toString();
    	model.addAttribute("time", time);
    	
    	HashMap<String, List<String>> errorMap = new HashMap<>();
    	
    	if (validations.hasErrors()) {
    		System.out.println("Hay errores");
    		
    		// Show errors in console
    		List<FieldError> errors = validations.getFieldErrors();
    		errors.forEach((error) -> {
    			System.out.println(error.getField() + ": " + error.getDefaultMessage());
    		});
    		
    		if (!errors.isEmpty())
    			model.addAttribute("hasErrors", true);
    		
    		// Map errors to display in UI
    		validations.getFieldErrors().stream().forEach(error -> {
    			String key = error.getField();
    			
    			List<String> itemErrors = errorMap.get(key);
    			if (itemErrors == null)
    				itemErrors = new ArrayList<>();
    			
    			itemErrors.add(error.getDefaultMessage());
    			errorMap.put(key, itemErrors);
    		});
    		model.addAllAttributes(errorMap);
    		
    		
    		return "main";
    	}
    	
    	System.out.println(bookInfo);
    	
    	Book newBook = new Book(bookInfo.getIsbn(), bookInfo.getTitle());
    	library.add(newBook);
    	
    	return "redirect:/all";
    }
    
    @PostMapping("/update")
    public String updateBook(@ModelAttribute SaveBookDTO bookInfo) {
    	
    	Book book = library.stream().filter(b -> b.getIsbn().equals(bookInfo.getIsbn())).findFirst().orElse(null);
    	
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
    	model.addAttribute("books", library);
    	return "book-list";
    }
    
    @GetMapping("/book/{isbn}")
    public String getUpdatePage(@PathVariable String isbn, Model model) {
    	String time = Calendar.getInstance().getTime().toString();
    	model.addAttribute("time", time);
    	
    	Book defaultBook = new Book("843760494X", "Cien Años de Soledad");
    	Book book = library.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(defaultBook);
    	
    	model.addAttribute("bookTitle", book.getTitle());
    	
    	return "update-book";    
    }
    
}
