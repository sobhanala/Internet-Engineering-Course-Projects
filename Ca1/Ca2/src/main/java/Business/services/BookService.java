package Business.services;

import Business.models.Admin;
import Business.models.Book;
import Business.models.Person;
import Repositorys.BookRepository;
import Repositorys.UserRepository;

import java.util.Optional;

public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void addBook(Book book,String username) {
        Optional<Person> p = userRepository.findByUsername(username);
        if (p.isEmpty()||!(p.get() instanceof Admin)){
            throw new IllegalArgumentException("person not found");
        }

        boolean Exist = bookRepository.existsByTitle(book.getTitle());
        if (Exist) {
            throw new IllegalArgumentException("User already exists");
        }
        bookRepository.saveBook(book);
    }
    public Book getBookDetail(String bookTitle) {
        Optional<Book> bookOptional = bookRepository.findByTitle(bookTitle);
        if (bookOptional.isEmpty()){
            throw new IllegalArgumentException("Book not found");
        }
        return bookOptional.get();

    }
}