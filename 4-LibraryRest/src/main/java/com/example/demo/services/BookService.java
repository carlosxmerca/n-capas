package com.example.demo.services;

import java.util.List;

import com.example.demo.models.entities.Book;

public interface BookService {
	void save(Book book);
	void delete(String isbn);
	Book findOneById(String isbn);
	List<Book> findAll();
}
