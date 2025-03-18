package Repositorys;

import Business.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findByTitle(String title);
    void saveBook(Book book);
    boolean existsByTitle(String title);
    List<Book> findBooksByTitleContaining(String titlePart);
    List<Book> findBooksByAuthorContaining(String authorPart);
    List<Book> findBooksByGenre(String genre);
    List<Book> findBooksByYearRange(int fromYear, int toYear);
}