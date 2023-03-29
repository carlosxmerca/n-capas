# Library MVC

## Book

```java
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
```

## SaveBookDTO

```java
package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveBookDTO {
    private String isbn;
    private String title;
    private String owner;
}
```

## LibraryController

```java
package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.entities.*;
import com.example.demo.models.dtos.*;

@Controller
public class LibraryController {
	
    private static List<Book> library = new ArrayList<>();
    
    static {
        library.add(new Book("0261102303", "Lord of the Rings"));
        library.add(new Book("0007441428", "Game of Thrones"));
        library.add(new Book("0747581088", "Harry Potter and the Half-Blood Prince"));
        library.add(new Book("1401248195", "Watchmen"));
        library.add(new Book("030788743X", "Ready player one"));
        library.add(new Book("843760494X", "Cien Años de Soledad"));
        library.add(new Book("0553804367", "A Briefer History of Time!!"));
    }
    
    @GetMapping("/all")
    public String getBookList(Model model) {
    	model.addAttribute("books", library);
    	return "book-list";
    }
    
    @PostMapping("/save")
    public String saveBook(@ModelAttribute SaveBookDTO bookInfo) {
    	System.out.println(bookInfo);
    	
    	Book newBook = new Book(bookInfo.getIsbn(), bookInfo.getTitle());
    	library.add(newBook);
    	
    	return "redirect:/";
    }
    
    @GetMapping("/")
    public String getMainPage(Model model) {
    	String time = Calendar.getInstance().getTime().toString();
    	model.addAttribute("time", time);
    	
    	return "main";    
    }
    
}
```

## Moustache

```html
<table>
    <thead>
    <tr>
        <th> ISBN </th>
        <th> Title </th>
    </tr>
    </thead>
    <tbody>
        {{ #books }}
        <tr>
        <td> {{ isbn }} </td>
        <td> {{ title }} </td>
        </tr>
        {{ /books }}
    </tbody>
</table>
```

```html
{{ >footer }}
```

### application.properties
```
spring.mustache.suffix=.html
```

### build.gradle
```
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.0.5'
	id 'io.spring.dependency-management' version '1.1.0'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-mustache'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
	useJUnitPlatform()
}
```