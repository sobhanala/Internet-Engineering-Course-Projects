package Business.services;

import Business.exeptions.ShopException;
import Business.models.*;
import Repositorys.BookRepository;
import Repositorys.ReviewRepository;
import Repositorys.UserRepository;

import java.util.List;
import java.util.Optional;

import static Business.exeptions.ShopException.ErrorType.*;

public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public void addBook(Book book,String username) {
        Optional<Person> p = userRepository.findByUsername(username);
        if (p.isEmpty()||!(p.get() instanceof Admin)){
            throw new ShopException(USER_NOT_ADMIN);
        }

        boolean Exist = bookRepository.existsByTitle(book.getTitle());
        if (Exist) {
            throw new ShopException(BOOK_ALREADY_EXISTS);
        }
        bookRepository.saveBook(book);
    }
    public Book getBookDetail(String bookTitle) {
        Optional<Book> bookOptional = bookRepository.findByTitle(bookTitle);
        if (bookOptional.isEmpty()){
            throw new ShopException(BOOK_NOT_FOUND);
        }
        return bookOptional.get();

    }
    public void addReview(String username, String title, int rate, String comment) {
        Optional<Person> personOpt = userRepository.findByUsername(username);
        if (personOpt.isEmpty()) {
            throw new ShopException(USER_NOT_FOUND);
        }
        Person person = personOpt.get();

        if (!(person instanceof Customer customer)) {
            throw new ShopException(USER_NOT_CUSTOMER);
        }

        Optional<Book> bookOpt = bookRepository.findByTitle(title);
        if (bookOpt.isEmpty()) {
            throw new ShopException(BOOK_NOT_FOUND);
        }
        Book book = bookOpt.get();
        try {
            Review review = new Review(customer, book, rate, comment);
            reviewRepository.saveReview(review);
        } catch (Exception e) {
            throw new ShopException(GENERIC_ERROR);
        }
    }
    public BookReviewShow showBookReview(String bookTitle){
        Optional<Book> bookOpt = bookRepository.findByTitle(bookTitle);
        if (bookOpt.isEmpty()) {
            throw new ShopException(BOOK_NOT_FOUND);

        }
        List<Review> reviewsByBookTitle = reviewRepository.findReviewsByBookTitle(bookTitle);

        double meanRating = reviewsByBookTitle.isEmpty()
                ? 0.0
                : reviewsByBookTitle.stream()
                .mapToDouble(Review::getRate)
                .average()
                .orElse(0.0);

        return new BookReviewShow(bookTitle, reviewsByBookTitle, meanRating);

    }
    public BookSearchData searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findBooksByTitleContaining(title);
        BookSearchData data = new BookSearchData(title, books);
        return data;
    }

    public BookSearchData searchBooksByAuthor(String author) {
        List<Book> books = bookRepository.findBooksByAuthorContaining(author);
        return new BookSearchData(author, books);
    }

    public BookSearchData searchBooksByGenre(String genre) {
        List<Book> books = bookRepository.findBooksByGenre(genre);
        return new BookSearchData(genre, books);
    }

    public BookSearchData searchBooksByYear(String from, String to) {
            int fromYear = Integer.parseInt(from);
            int toYear = Integer.parseInt(to);
            if (fromYear > toYear) {
            throw new IllegalArgumentException("ji");
            }
            List<Book> books = bookRepository.findBooksByYearRange(fromYear, toYear);
            String searchRange = from + "-" + to;
            return  new BookSearchData(searchRange, books);

    }


}