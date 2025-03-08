package Business.services;

import Business.exeptions.ShopException;
import Business.models.*;
import Repositorys.BookRepository;
import Repositorys.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static Business.exeptions.ShopException.ErrorType.*;


public class ShopService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    public ShopService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void addCart(PendPurches cart) {
        Optional<Book> book = bookRepository.findByTitle(cart.title());
        Optional<Person> person = userRepository.findByUsername(cart.username());
        if (book.isEmpty()){
            throw new ShopException(BOOK_NOT_FOUND);
        }
        if (person.isEmpty()){
            throw new ShopException(USER_NOT_FOUND);
        }
        Person p = person.get();

        if (!(p instanceof Customer customer)){
            throw new ShopException(USER_NOT_CUSTOMER);
        }
        customer.addToCart(book.get());
    }
    public void removeCart(PendPurches cart) {
        Optional<Book> book = bookRepository.findByTitle(cart.title());
        Optional<Person> person = userRepository.findByUsername(cart.username());

        if (book.isEmpty()) {
            throw new ShopException(BOOK_NOT_FOUND);
        }
        if (person.isEmpty()) {
            throw new ShopException(USER_NOT_FOUND);
        }

        Person p = person.get();
        if (!(p instanceof Customer customer)) {
            throw new ShopException(USER_NOT_CUSTOMER);
        }

        customer.removeFromCart(book.get());

    }
    public Receipt  purchase(String username){
        Optional<Person> person = userRepository.findByUsername(username);
        if (person.isEmpty()){
            throw new ShopException(USER_NOT_FOUND);
        }
        Person p = person.get();
        if (!(p instanceof Customer customer)){
            throw new ShopException(USER_NOT_CUSTOMER);
        }
        Receipt receipt = CreateReceipt(customer);
        customer.finalisePurchase();
        return receipt;


    }
    private Receipt CreateReceipt(Customer customer){
        int buyedcount = customer.getCart().getBooks().size();
        return new Receipt(buyedcount, customer.getCartTotalPrice(), new Date());
    }
    public void borrowBook(Borrowdata borrowdata) {
        if (borrowdata.days() < 1 || borrowdata.days() > 9) {
            throw new ShopException(INVALID_DATA);
        }

        Optional<Person> personOpt = userRepository.findByUsername(borrowdata.username());
        if (personOpt.isEmpty()) {
            throw new ShopException(USER_NOT_FOUND);
        }
        Person person = personOpt.get();
        if (!(person instanceof Customer customer)) {
            throw new ShopException(USER_NOT_CUSTOMER);
        }

        Optional<Book> bookOpt = bookRepository.findByTitle(borrowdata.title());
        if (bookOpt.isEmpty()) {
            throw new ShopException(BOOK_NOT_FOUND);
        }
        Book book = bookOpt.get();

        customer.addBorrow(book, borrowdata.days());

    }

    public PurchaseResponse showPurchaseHistory(String username) {
        Optional<Person> personOpt = userRepository.findByUsername(username);
        if (personOpt.isEmpty()) {
            throw new ShopException(USER_NOT_FOUND);
        }
        Person person = personOpt.get();

        if (!(person instanceof Customer customer)) {
            throw new ShopException(USER_NOT_CUSTOMER);
        }
        return new PurchaseResponse(username, customer.getPurchaseHistory());
    }

    public BookInCart showCart(String username) {
        Optional<Person> personOpt = userRepository.findByUsername(username);
        if (personOpt.isEmpty()) {
            throw new ShopException(USER_NOT_FOUND);
        }
        Person person = personOpt.get();

        if (!(person instanceof Customer customer)) {
            throw new ShopException(USER_NOT_CUSTOMER);
        }

        Cart cart = customer.getCart();
        List<BorrowDetails> items = cart.getBorrowDetails();

        return new BookInCart(username,customer.getCartTotalPrice(),items);
    }
    public PurchasedBooksData showPurchasedBooks(String username) {
        Optional<Person> personOpt = userRepository.findByUsername(username);
        if (personOpt.isEmpty()) {
            throw new ShopException(USER_NOT_FOUND);
        }
        Person person = personOpt.get();

        if (!(person instanceof Customer customer)) {
            throw new ShopException(USER_NOT_CUSTOMER);

        }

        customer.cleanExpiredBooks();
        List<PurchaseItem> books = new ArrayList<>();

        for (Book book : customer.getBoughtBooks()) {
            books.add(new PurchaseItem(book, false, 0, book.getPrice()));
        }

        for (BorrowedBook borrowed : customer.getBorrowedBooks()) {
            Book book = borrowed.book();
            int borrowDays = customer.getPurchaseHistory().stream()
                    .flatMap(record -> record.items().stream())
                    .filter(item -> item.book().equals(book) && item.isBorrowed())
                    .mapToInt(PurchaseItem::borrowDays)
                    .findFirst()
                    .orElse(0);
            double finalPrice = (borrowDays / 10.0) * book.getPrice();
            books.add(new PurchaseItem(book, true, borrowDays, finalPrice));
        }

        PurchasedBooksData data = new PurchasedBooksData(username, books);
        return data;
    }
}

