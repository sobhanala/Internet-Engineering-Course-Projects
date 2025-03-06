package Business.services;

import Business.models.Book;
import Repositorys.BookRepository;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public void addBook(Book book) {
        boolean Exist = bookRepository.existsByTitle(book.getUsername());
        if (Exist) {
            throw new IllegalArgumentException("User already exists");
        }
        bookRepository.saveBook(book);
    }
}