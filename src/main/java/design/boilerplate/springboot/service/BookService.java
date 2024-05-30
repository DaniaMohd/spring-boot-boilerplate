package design.boilerplate.springboot.service;

import design.boilerplate.springboot.model.Book;
import design.boilerplate.springboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List; // to get List<T>
import java.util.Optional;

import javax.persistence.EntityNotFoundException;


@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    //Java Constructor
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    public Book findByID(Long Id){
        return bookRepository.getReferenceById(Id);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    /**
     * Creates a book and saves it to the database
     * @param book
     */
    public void CreateBook(Book book){
        bookRepository.save(book);
    }

    /**
     * get book by id in a safe way
     * @param id
     * @return optional book that can be tested to see if its valid or not
     */
    public Optional<Book> getBookByIDSafe(Long id){
        return bookRepository.findById(id);
    }

    /**
     * finds the book to be updated & updates the book
     * @param id
     * @param book
     * @return
     */
    public Book updateBook(Long id, Book book){
        if (id == null) {
            throw new IllegalArgumentException("books ID cannot be found");
        }

        if(bookRepository.existsById(id) == false) {
            throw new EntityNotFoundException("Book not found");
        }

        Book currentBook = bookRepository.findById(id).get();

        //update this when there are new properties to be added to the books
        currentBook.setTitle(book.getTitle());

        return bookRepository.save(currentBook);
    }

    /**
     * Deleting a book by finding its ID
     * @param id
     */
    public void deleteBook(Long id){
        if (id == null){
            throw new IllegalArgumentException("books ID cannot be found");
        }

        if(bookRepository.existsById(id) == false){
            throw new EntityNotFoundException("Book not found");
        }

        bookRepository.deleteById(id);
    }
}
