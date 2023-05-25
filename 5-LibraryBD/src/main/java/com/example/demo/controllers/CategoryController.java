package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.SaveCategoryDTO;
import com.example.demo.models.entities.Category;
import com.example.demo.services.CategoryServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryServices categoryService;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAllCategories() {
		List<Category> categories = categoryService.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOneCategory(@PathVariable(name = "id") String id) {
		Category category = categoryService.findOneById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveCategory(@ModelAttribute @Valid SaveCategoryDTO info, BindingResult validations) {
		if (validations.hasErrors()) {
			return new ResponseEntity<>("La cago", HttpStatus.BAD_REQUEST);
		}
		
		try {
			categoryService.save(info);
			return new ResponseEntity<>("Buenale", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
