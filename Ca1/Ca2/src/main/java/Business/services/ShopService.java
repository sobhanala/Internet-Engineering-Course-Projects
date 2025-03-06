package Business.services;

import Business.models.*;
import Repositorys.BookRepository;
import Repositorys.UserRepository;

import java.util.Date;
import java.util.Optional;

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
            throw new IllegalArgumentException("book not found");
        }
        if (person.isEmpty()){
            throw new IllegalArgumentException("user not found");
        }
        Person p = person.get();

        if (!(p instanceof Customer customer)){
            throw new IllegalArgumentException("user not found");
        }
        customer.AddToCart(book.get());
    }
    public void removeCart(PendPurches cart) {
        Optional<Book> book = bookRepository.findByTitle(cart.title());
        Optional<Person> person = userRepository.findByUsername(cart.username());

        if (book.isEmpty()) {
            throw new IllegalArgumentException("Book not found");
        }
        if (person.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Person p = person.get();
        if (!(p instanceof Customer customer)) {
            throw new IllegalArgumentException("User is not a customer");
        }

        customer.RemoveFromCart(book.get());

    }
    public Receipt  purchase(String username){
        Optional<Person> person = userRepository.findByUsername(username);
        if (person.isEmpty()){
            throw new IllegalArgumentException("user not found");
        }
        Person p = person.get();
        if (!(p instanceof Customer customer)){
            throw new IllegalArgumentException("user not found");
        }
        double total=customer.PurchaseCart();
        Receipt receipt = CreateReceipt(customer,total);
        customer.EmptyCart();
        return receipt;


    }
    private Receipt CreateReceipt(Customer customer,double total ){
        return new Receipt(customer.getCart().books.size(),total,new Date());
    }

}
