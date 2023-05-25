package com.example.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.models.entities.Book;

public interface BookRepository extends ListCrudRepository<Book, UUID> {

	List<Book> findByIsbn(String Isbn);
	
}
