package com.example.demo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * @Data -> Generates setters y getters in compilation time
 * @AllArgsConstructor -> Generates constructors in compilation time
 * 
 * */

@Data
@AllArgsConstructor
public class Book {
	private String isbn;
	private String title;
}
