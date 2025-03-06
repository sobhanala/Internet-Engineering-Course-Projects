package Business;

import Business.models.*;

public class ShopService {

    public String addAuthor(Author author) {
        boolean Exist = DataLoader.CheckAuthorUniqneness(author.getUsername());
        if (Exist) {
            throw new IllegalArgumentException("Author already exists");
        }
        DataLoader.AddAuthor(author);
        return "Author added successfully";
    }

    public String addCart(PendPurches cart) {
        Book book = DataLoader.getBookByTitle(cart.title());
        Person person = DataLoader.getUserByUsername(cart.username());
        if (book==null){
            throw new IllegalArgumentException("book not found");
        }
        if (!(person instanceof Customer)){
            throw new IllegalArgumentException("user not found");
        }

        ((Customer) person).AddToCart(book);

        return "Added book to cart.";
    }

    public String removeCart(PendPurches cart) {
        Book book = DataLoader.getBookByTitle(cart.title());
        Person person = DataLoader.getUserByUsername(cart.username());
        if (book==null){
            throw new IllegalArgumentException("book not found");
        }
        if (!(person instanceof Customer)){
            throw new IllegalArgumentException("user not found");
        }

        ((Customer) person).AddToCart(book);

        return "Added book to cart.";
    }

    public String addBook(Book book) {
        boolean Exist = DataLoader.CheckBookUniqneness(book.getUsername());
        if (Exist) {
            throw new IllegalArgumentException("User already exists");
        }
        DataLoader.AddBook(book);
        return "Book added successfully";
    }

    public String addUser(Person person) {
        boolean Exist = DataLoader.CheckUserUniqneness(person.getUsername(), person.getEmail());
        if (Exist) {
            throw new IllegalArgumentException("User already exists");
        }
        DataLoader.AddUser(person);
        return "User added successfully";
    }
}
