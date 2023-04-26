# Library RestAPI

- main
    - controllers
    - models
        - dtos
        - entities
    - service
        - implementations
    - utils

## Entities
### Book
```java
@Data
@AllArgsConstructor
public class Book {
	private String isbn;
	private String title;
	private Date publishDate;
	private String url;
}
```

### User
```java
@Data
@AllArgsConstructor
public class User {
	private String username;
	private String email;
	private String name;
	
	@JsonIgnore
	private String password;
}
```

## DTOs

```java
@Data
@AllArgsConstructor
public class BookDTO {
    @NotNull(message = "isbn required")
    @Size(min = 10, max = 10, message = "Isbn must be 10 characters")
    private String isbn;

    @NotNull(message = "title required")
    private String title;

    @NotNull(message = "publish date required")
    private String publishDate;

    @NotNull(message = "url required")
    @NotEmpty(message = "url must not be empty")
    private String url;
}
```

```java
@Data
@AllArgsConstructor
public class SearchBookDTO {
	@NotEmpty(message = "Isbn required")
	@Size(min = 10, max = 10, message = "Isbn must be 10 characters")
	private String isbn;
	
	@NotEmpty(message = "Identifier required")
	private String identifier;
}
```

## Services
### BookService
```java
public interface BookService {
    void save(Book book);
    void delete(String isbn);
    Book findOneById(String isbn);
    List<Book> findAll();
}
```

```java
@Service
public class BookServiceImplementation implements BookService {
	
    private static List<Book> library = new ArrayList<>();
    private static DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        
    static {
        try {
            library.add(new Book("0261102303", "Lord of the Rings", date.parse("01/01/1975"), "https://loremflickr.com/1600/2560"));
            library.add(new Book("0007441428", "Game of Thrones v2", date.parse("01/01/1999"), "https://loremflickr.com/1600/2560"));
            library.add(new Book("0747581088", "Harry Potter and the Half-Blood Prince", date.parse("01/01/2007"), "https://loremflickr.com/1600/2560"));
            library.add(new Book("1401248195", "Watchmen", date.parse("01/01/1970"), "https://loremflickr.com/1600/2560"));
            library.add(new Book("030788743X", "Ready player one v2", date.parse("01/01/2010"), "https://loremflickr.com/1600/2560"));
            library.add(new Book("843760494X", "Cien Años de Soledad", date.parse("01/01/1982"), "https://loremflickr.com/1600/2560"));
            library.add(new Book("0553804367", "A Briefer History of Time", date.parse("01/01/1990"), "https://loremflickr.com/1600/2560"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Book book) {
        Stream<Book> filteredStream = library.stream()
                .filter(b -> !b.getIsbn().equals(book.getIsbn()));
        
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

### UserService
```java
public interface UserService {
    void register(RegisterDTO info);
    User findOneById(String id);
}
```

```java
@Service
public class UserServiceImplementation implements UserService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("douglashdezt", "dohernandez@uca.edu.sv", "Douglas Hernandez", "12345678Aa!"));
        users.add(new User("naldana", "naldana@uca.edu.sv", "Néstor Aldana", "12345678Aa!"));
        users.add(new User("ecalderon", "ealdana@uca.edu.sv", "Elisa Aldana", "12345678Aa!"));
        users.add(new User("enxel", "earaujo@uca.edu.sv", "Enmanuel Amaya", "12345678Aa!"));
        users.add(new User("evarela", "evarela@uca.edu.sv", "Erick Varela", "12345678Aa!"));
        users.add(new User("armandoCodigos", "rcanizales@uca.edu.sv", "Ronaldo Canizales", "12345678Aa!"));
    }

    @Override
    public void register(RegisterDTO info) {
        User newUser = new User(
                info.getUsername(),
                info.getEmail(),
                null,
                info.getPassword() + "_encrypted");
        
        users = Stream.concat(users.stream(), Stream.of(newUser))
                .collect(Collectors.toList());
    }

    @Override
    public User findOneById(String id) {
        return users.stream()
                .filter(u -> (u.getUsername().equals(id) || u.getEmail().equals(id)))
                .findAny()
                .orElse(null);
    }
}
```
## Controllers
### BookController
```java
@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private RequestErrorHandler errorHandler;
    private static DateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    @GetMapping("")
    public ResponseEntity<?> getBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooks(@PathVariable String id) {
        Book book = bookService.findOneById(id);
        
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveBook(@Valid BookDTO info, BindingResult validations) {
        
        if (validations.hasErrors())
            return new ResponseEntity<>(
                    errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        
        try {
            Book newBook = new Book(info.getIsbn(), info.getTitle(), date.parse(info.getPublishDate()), info.getUrl()); 
            bookService.save(newBook);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        Book book = bookService.findOneById(id);
        
        if (book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
```

### LibraryController
```java
@RestController
@RequestMapping("/library")
@CrossOrigin("*")
public class LibraryController {
	
    @Autowired
    private UserService userServie;
    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<?> findOneBookByIsbnAndUser(
            @Valid SearchBookDTO search, BindingResult validations) {
        
        if (validations.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        User userFound = userServie.findOneById(search.getIdentifier());
        if (userFound == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        Book bookFound = bookService.findOneById(search.getIsbn());
        if (bookFound == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        BookUserDTO response = new BookUserDTO(bookFound, userFound);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
```

## Utils
```java
@Component
public class RequestErrorHandler {
    public Map<String, List<String>> mapErrors(List<FieldError> fieldErrors) {
        HashMap<String, List<String>> errorMap = new HashMap<>();

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