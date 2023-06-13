package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.models.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	// public List<Category> findAllByName(String name);
}
