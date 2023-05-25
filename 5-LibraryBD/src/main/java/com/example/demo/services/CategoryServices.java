package com.example.demo.services;

import java.util.List;

import com.example.demo.models.dtos.SaveCategoryDTO;
import com.example.demo.models.entities.Category;

public interface CategoryServices {
	public void save(SaveCategoryDTO info) throws Exception;
	public void deleteById(String code) throws Exception;
	public List<Category> findAll();
	public Category findOneById(String code);
}
