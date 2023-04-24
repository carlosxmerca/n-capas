package com.example.demo.models.dto;

import com.example.demo.models.entities.Book;
import com.example.demo.models.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookUserDTO {
	private Book book;
	private User user;
}
