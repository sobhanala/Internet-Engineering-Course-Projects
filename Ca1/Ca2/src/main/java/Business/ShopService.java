package Business;

import Business.models.*;

import java.util.Optional;

public class ShopService {

    public String addAuthor(Author author) {
        boolean Exist = DataLoader.checkAuthorUniqueness(author.getUsername());
        if (Exist) {
            throw new IllegalArgumentException("Author already exists");
        }
        DataLoader.addAuthor(author);
        return "Author added successfully";
    }

    public String addCart(PendPurches cart) {
        Optional<Book> book = DataLoader.getBookByTitle(cart.title());
        Optional<Person> person = DataLoader.getUserByUsername(cart.username());
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
        return "Added book to cart.";
    }

    public String removeCart(PendPurches cart) {
        Optional<Book> book = DataLoader.getBookByTitle(cart.title());
        Optional<Person> person = DataLoader.getUserByUsername(cart.username());

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

        return "Removed book from cart.";
    }

    public String addBook(Book book) {
        boolean Exist = DataLoader.checkBookUniqueness(book.getUsername());
        if (Exist) {
            throw new IllegalArgumentException("User already exists");
        }
        DataLoader.addBook(book);
        return "Book added successfully";
    }

    public String addUser(Person person) {
        boolean Exist = DataLoader.checkUserUniqueness(person.getUsername(), person.getEmail());
        if (Exist) {
            throw new IllegalArgumentException("User already exists");
        }
        DataLoader.addUser(person);
        return "User added successfully";
    }
}
