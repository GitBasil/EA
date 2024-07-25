package mvc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mvc.domain.Book;
import mvc.domain.Books;


@RestController
@RequestMapping("/books")
public class BookController {
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        System.out.println("Adding book: " + book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        System.out.println("Updating book: " + book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        System.out.println("Deleting book: " + isbn);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        System.out.println("Get Book book: " + isbn);
        Book book = new Book(isbn, isbn, isbn, 0);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Books> getBooks() {
        System.out.println("Get All Books");
        Book book = new Book("isbn", "author", "title", 0);
        Books books = new Books();
        books.setBooks(List.of(book));
        return new ResponseEntity<Books>(books, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Books> searchBooks(@RequestParam String author) {
        System.out.println("Search Books by author: " + author);
        Book book = new Book("isbn", author, "title", 0);
        Books books = new Books();
        books.setBooks(List.of(book));
        return new ResponseEntity<Books>(books, HttpStatus.OK);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleExceptions(Exception exception) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("isSuccess", false);
//        map.put("error", exception.getMessage());
//        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}



