package com.example.demo.services;

import com.example.demo.models.entities.Book;
import com.example.demo.models.entities.User;

public interface BookLoansServices {
	void createLoan(Book book, User user) throws Exception;
	void closeBookLoan(String bookCode) throws Exception;
}
