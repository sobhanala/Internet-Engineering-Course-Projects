package Repositorys;

import Business.models.Book;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findByTitle(String title);
    void saveBook(Book book);
    boolean existsByTitle(String title);
}