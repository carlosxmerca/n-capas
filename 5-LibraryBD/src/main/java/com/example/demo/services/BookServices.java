package com.example.demo.services;

import java.util.List;

import com.example.demo.models.dtos.SaveBookDTO;
import com.example.demo.models.entities.Book;
import com.example.demo.models.entities.Category;
import com.example.demo.models.entities.User;
import org.springframework.data.domain.Page;

public interface BookServices {
	void save(SaveBookDTO data, Category category) throws Exception;
	void deleteById(String id) throws Exception;
	Book findOneById(String id);
	List<Book> findAll();
	Page<Book> findAll(String title, int page, int size);
	List<Book> findAllByISBN(String isbn);
	Book findByCode(String code);
	
	void createLoan(Book book, User user) throws Exception;
}
