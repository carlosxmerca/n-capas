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

# Library MVC v2

### Structure
- main
    - controllers
    - models
        - dtos
        - entities
    - service
        - implementations
    - utils
- resources
    - templates

## Entities
### Book

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

## DTOs
### SaveBookDTO
```java
@Data
@AllArgsConstructor
public class SaveBookDTO {
    @NotEmpty(message = "ISBN vacio")
    @Size(min = 10, max = 10, message = "Debe tener 10 caracteres")
    private String isbn;

    @NotEmpty(message = "Titulo vacio")
    private String title;

    @NotEmpty(message = "Dueño vacio")
    @Email(message = "Formato de dueño incorrecto")
    // @Pattern(regexp = "")
    private String owner;
}
```

## Service
```java
public interface BookService {
    void save(Book book);
    void delete(String isbn);
    Book findOneById(String isbn);
    List<Book> findAll();
}
```

## Service Implementation
```java
@Service
public class BookServiceImpl implements BookService {
	
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

    @Override
    public void save(Book book) {
        Stream<Book> filteredStream = library.stream()
            .filter(b -> {
                return !b.getIsbn().equals(book.getIsbn());
            });
        
        library = Stream.concat(filteredStream, Stream.of(book))
                        .collect(Collectors.toList());
    }

    @Override
    public void delete(String isbn) {
        library = library.stream()
            .filter(b -> !b.getIsbn().equals(isbn))
            .collect(Collectors.toList());
    }

    @Override
    public Book findOneById(String isbn) {
        return library.stream()
            .filter(b -> b.getIsbn().equals(isbn))
            .findAny()
            .orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return library;
    }
}
```

## Utils
### RequestErrorHandler
```java
// @Component -> forma generica de crear un bean

@Component
public class RequestErrorHandler {
    public Map<String, List<String>> mapErrors(List<FieldError> fieldErrors) {
        HashMap<String, List<String>> errorMap = new HashMap<>();
        // Map errors to display in UI
        fieldErrors.stream().forEach(error -> {
            String key = error.getField();
            
            List<String> itemErrors = errorMap.get(key);
            if (itemErrors == null)
                itemErrors = new ArrayList<>();
            
            itemErrors.add(error.getDefaultMessage());
            errorMap.put(key, itemErrors);
        });
        
        return errorMap;
    }
}
```

## LibraryController

```java
@Controller
public class LibraryController {
    
    @Autowired
    private BookService bookService;
    @Autowired
    private RequestErrorHandler errorHandler;
        
    @PostMapping("/save")
    public String saveBook(@ModelAttribute @Valid SaveBookDTO bookInfo,
            BindingResult validations, Model model) {
        
        String time = Calendar.getInstance().getTime().toString();
        model.addAttribute("time", time);
        
        if (validations.hasErrors()) {
            System.out.println("Hay errores");
            
            // Show errors in console
            List<FieldError> errors = validations.getFieldErrors();
            errors.forEach((error) -> {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
            });
            
            if (!errors.isEmpty())
                model.addAttribute("hasErrors", true);
            
            model.addAllAttributes(errorHandler.mapErrors(errors));
            
            return "main";
        }
        
        System.out.println(bookInfo);
        
        Book newBook = new Book(bookInfo.getIsbn(), bookInfo.getTitle());
        // library.add(newBook);
        bookService.save(newBook);
        
        return "redirect:/all";
    }
    
    @PostMapping("/update")
    public String updateBook(@ModelAttribute SaveBookDTO bookInfo) {
        
        // Book book = library.stream().filter(b -> b.getIsbn().equals(bookInfo.getIsbn())).findFirst().orElse(null);
        Book book = bookService.findOneById(bookInfo.getIsbn());
        
        if (book != null) {
            book.setTitle(bookInfo.getTitle());
            System.out.println(book);
        }
        
        return "redirect:/all";
    }
    
    @GetMapping("/")
    public String getMainPage(Model model) {
        String time = Calendar.getInstance().getTime().toString();
        model.addAttribute("time", time);
        
        return "main";    
    }
    
    @GetMapping("/all")
    public String getBookList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }
    
    @GetMapping("/book/{isbn}")
    public String getUpdatePage(@PathVariable String isbn, Model model) {
        String time = Calendar.getInstance().getTime().toString();
        model.addAttribute("time", time);
        
        Book defaultBook = new Book("843760494X", "Cien Años de Soledad");
        // Book book = library.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(defaultBook);
        Book book = bookService.findOneById(isbn);
        
        model.addAttribute("bookTitle", book.getTitle());
        
        return "update-book";    
    }
}
```

## Save book form

```html
<main>
    <h1> Library (<a href="/library/all"> Ver Libros </a>) </h1>
    <h2> {{ time }} </h2>
    <form class="{{#hasErrors}}error{{/hasErrors}}" action="/save" method="POST">
        <label for="isbn-field">
        ISBN:
        <input type="text" name="isbn" id="isbn-field" required>
        {{#isbn}}
            <p class="err">{{ . }}</p>
        {{/isbn}}
        </label>


        <label for="title-field">
        Title:
        <input type="text" name="title" id="title-field" required>
        {{#title}}
            <p class="err">{{ . }}</p>
        {{/title}}
        </label>

        <label for="owner-field">
        Owner:
        <input type="text" name="owner" id="owner-field" required>
            {{#owner}}
            <p class="err">{{ . }}</p>
        {{/owner}}
        </label>

        <input type="submit" value="Register">
    </form>
</main>
```