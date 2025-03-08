package Repositorys;

import Business.models.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {
    private final List<Book> bookList = new ArrayList<>();

    public InMemoryBookRepository() {
        bookList.add(new Book( " ", " ", " ", 0, 0, " ", " ", new ArrayList<>()));
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookList.stream()
                .filter(b -> b.getTitle().equals(title))
                .findAny();
    }

    @Override
    public void saveBook(Book book) {
        bookList.add(book);
    }

    @Override
    public boolean existsByTitle(String title) {
        return bookList.stream()
                .anyMatch(b -> b.getTitle().equals(title));
    }
    @Override
    public List<Book> findBooksByTitleContaining(String titlePart) {
        return bookList.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(titlePart.toLowerCase()))
                .toList();
    }

    @Override
    public List<Book> findBooksByAuthorContaining(String authorPart) {
        return bookList.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(authorPart.toLowerCase()))
                .toList();
    }

    @Override
    public List<Book> findBooksByGenre(String genre) {
        return bookList.stream()
                .filter(b -> b.getGenres().contains(genre))
                .toList();
    }

    @Override
    public List<Book> findBooksByYearRange(int fromYear, int toYear) {
        return bookList.stream()
                .filter(b -> b.getYear() >= fromYear && b.getYear() <= toYear)
                .toList();
    }

}