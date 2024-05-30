package design.boilerplate.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import design.boilerplate.springboot.service.BookService;
import org.springframework.web.bind.annotation.*;

import design.boilerplate.springboot.model.Book;
import java.util.List; // to get List<T>

import java.util.Optional;

@RestController
public class ApiController {

	private final BookService bookService;

	@Autowired
    //Java Constructor
    public ApiController(BookService bookService){
        this.bookService = bookService;
    }

	/**
	 * @GetMapping is to retrieve existing books in the server.
	 * When u typed '../Get' at the end of the URL in the 
	 * web browser, the web browser automatically sends a Get
	 * command. u can do the same in the terminal with the command,
	 * "curl - X 'GET'".
	 */
	@GetMapping("/Get")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello Spring Boot Boilerplate");
	}

	@GetMapping("/GetAllBooks")
	public ResponseEntity<List<Book>> getAllBooks(){
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	/**
	 * @PostMapping telling the server that you want to post a new book.
	 * currently we want to use 'curl -X POST' in the command
	 * to post the new book.
	 * @param book
	 * @return
	 */
	@PostMapping("/PostBook")
	public ResponseEntity<String> postBook(@RequestBody Book book){
		//posts or creates a book into the database
		bookService.CreateBook(book); 
		return ResponseEntity.ok("Book posted");
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookByID(@PathVariable Long id) {
		Optional<Book> book = bookService.getBookByIDSafe(id);
		return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// @PostMapping("/search")
	// public void Search(@PathVariable String userSearchTerms){
		
	// }
	
	/**
	 * @PutMapping updates the book with id
	 * @param id
	 * @param book
	 * @return
	 */
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
		Book updatedBook = bookService.updateBook(id, book);
		return ResponseEntity.ok(updatedBook);
	}

	/**
	 * @DeleteMapping deletes the book with id
	 */
	@DeleteMapping("/books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return ResponseEntity.ok("Book deleted successfully.");
	}

	/** 
	 * Implement your APIs here. You may create other files where neccesary.
	 */

	 

}
