package com.example.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {

	List<Book> findByIsbn(String Isbn);
	Page<Book> findByTitleContains(String title, Pageable pageable);
}
