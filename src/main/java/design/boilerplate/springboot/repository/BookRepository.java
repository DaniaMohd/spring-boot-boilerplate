package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BookRepository inherits Jpa helper functions
 */
public interface BookRepository extends JpaRepository<Book, Long>{
}
