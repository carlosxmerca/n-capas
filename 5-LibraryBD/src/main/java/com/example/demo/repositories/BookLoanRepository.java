package com.example.demo.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.models.entities.BookLoan;

public interface BookLoanRepository extends ListCrudRepository<BookLoan, UUID> {

}
