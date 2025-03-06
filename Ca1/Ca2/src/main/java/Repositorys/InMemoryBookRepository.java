package Repositorys;

import Business.models.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {
    private final List<Book> bookList = new ArrayList<>();

    public InMemoryBookRepository() {
        bookList.add(new Book(" ", " ", " ", " ", 0, 0, " ", " ", new ArrayList<>()));
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
}