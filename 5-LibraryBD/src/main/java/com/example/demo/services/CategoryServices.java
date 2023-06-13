package com.example.demo.services;

import java.util.List;

import com.example.demo.models.dtos.SaveCategoryDTO;
import com.example.demo.models.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryServices {
	public void save(SaveCategoryDTO info) throws Exception;
	public void deleteById(String code) throws Exception;
	public List<Category> findAll();
	public Page<Category> findAll(int page, int size);
	public Category findOneById(String code);
}
