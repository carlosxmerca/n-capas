package com.example.demo.services.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dtos.BookLoanDTO;
import com.example.demo.models.entities.Book;
import com.example.demo.models.entities.BookLoan;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.BookLoanRepository;
import com.example.demo.services.BookLoansServices;

@Service
public class BookLoansServiceImpl implements BookLoansServices {
	
	@Autowired
	private BookLoanRepository repository;

	@Override
	public void closeBookLoan(String bookCode) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createLoan(Book book, User user) throws Exception {
		BookLoan loan = new BookLoan(
				new Date(),
				new Date(),
				user,
				book
				);
		
		repository.save(loan);	
	}

}
