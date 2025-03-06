package Business;

import Business.models.Author;
import Business.models.Book;
import Business.models.Person;

public class ShopService {

    public String addAuthor(Author author) {
        DataLoader.AddAuthor(author);
        return "Author added successfully";
    }

    public String addCart(Author author) {
        return "null";
    }

    public String addBook(Book book) {
        DataLoader.AddBook(book);
        return "Book added successfully";
    }

    public String addUser(Person person) {
        DataLoader.AddUser(person);
        return "User added successfully";
    }
}
