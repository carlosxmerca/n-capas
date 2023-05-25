package com.example.demo.services.implementations;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.BookLoanDTO;
import com.example.demo.models.dtos.SaveBookDTO;
import com.example.demo.models.entities.Book;
import com.example.demo.models.entities.BookLoan;
import com.example.demo.models.entities.Category;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.BookServices;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookServices {
	
	@Autowired
	private BookRepository repository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SaveBookDTO data, Category category) throws Exception {
		Book book = new Book(
				data.getIsbn(),
				data.getTitle(),
				data.getPublishDate(),
				category
				);
		
		repository.save(book);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteById(String id) throws Exception {
		UUID code = UUID.fromString(id);
		repository.deleteById(code);
	}

	@Override
	public Book findOneById(String id) {
		try {
			UUID code = UUID.fromString(id);
			return repository.findById(code).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Book> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Book> findAllByISBN(String isbn) {
		return repository.findByIsbn(isbn);
	}

	@Override
	public Book findByCode(String code) {
		try {
			UUID _code = UUID.fromString(code);
			return repository.findById(_code).orElse(null);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void createLoan(Book book, User user) throws Exception {
		BookLoan loan = new BookLoan(
				new Date(),
				new Date(),
				user,
				book
				);
		
		List<BookLoan> loans = book.getBookLoans();
		loans.add(loan);
		
		repository.save(book);
	}

}
