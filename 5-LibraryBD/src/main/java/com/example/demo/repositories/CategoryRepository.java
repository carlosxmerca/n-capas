package com.example.demo.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.models.entities.Category;

public interface CategoryRepository extends ListCrudRepository<Category, String> {
	// public List<Category> findAllByName(String name);
}
