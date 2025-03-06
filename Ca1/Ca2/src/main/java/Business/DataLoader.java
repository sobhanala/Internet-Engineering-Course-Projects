package Business;

import Business.models.Address;
import Business.models.Author;
import Business.models.Book;
import Business.models.Person;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ToString
public class DataLoader {
    private static final List<Book> bookList = new ArrayList<>();
    private static final List<Person> usersList = new ArrayList<>();
    private static final List<Author> authorList = new ArrayList<>();

    private DataLoader() {
        throw new UnsupportedOperationException("DataLoader is a static utility class and cannot be instantiated.");
    }
    static {
            bookList.add(new Book(" ", " "," "," ",0,0.0," "," ",new ArrayList<>()));
            usersList.add(new Person("defaultUser", "defaultEmail@gmail.com", "defaultEmail@gmail.com", new Address("defaultCountry", "defaultCity")));
            authorList.add(new Author("defaultUsername", "defaultName", "defaultPenName", null, null));
    }
    public static boolean checkUserUniqueness(String username, String email) {
        return usersList.stream()
                .anyMatch(person -> person.getUsername().equals(username)
                        && person.getEmail().equals(email));
    }

    public static void addBook(Book book) {
        bookList.add(book);
    }

    public static void addUser(Person person) {
        usersList.add(person);
    }

    public static void addAuthor(Author author) {
        authorList.add(author);
    }

    public static Optional<Person> getUserByUsername(String username) {
        return usersList.stream()
                .filter(p -> p.getUsername().equals(username))
                .findAny();
    }

    public static Optional<Book> getBookByTitle(String title) {
        return bookList.stream()
                .filter(b -> b.getTitle().equals(title))
                .findAny();
    }

    public static boolean checkAuthorUniqueness(String username) {
        return authorList.stream()
                .anyMatch(author -> author.getUsername().equals(username));
    }

    public static boolean checkBookUniqueness(String bookname) {
        return bookList.stream()
                .anyMatch(book -> book.getTitle().equals(bookname));
    }

}
