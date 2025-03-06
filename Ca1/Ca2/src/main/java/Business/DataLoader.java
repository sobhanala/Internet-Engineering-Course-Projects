package Business;

import Business.models.Author;
import Business.models.Book;
import Business.models.Person;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class DataLoader {
    private static final List<Book> bookList = new ArrayList<>();
    private static final List<Person> usersList = new ArrayList<>();
    private static final List<Author> authorList = new ArrayList<>();

    private DataLoader() {
        throw new UnsupportedOperationException("DataLoader is a static utility class and cannot be instantiated.");
    }
    static {
        bookList.add(new Book());
        usersList.add(new Person());
        authorList.add(new Author());
    }
    public static boolean CheckUserUniqneness(String username, String email){
        return usersList.stream().filter(person -> person.getUsername().equals(username) && person.getEmail().equals(email)).findFirst().orElse(null) != null;
    }

    public static void AddBook(Book book){
        bookList.add(book);
    }
    public static  void AddUser(Person person){
        usersList.add(person);
    }
    public static  void AddAuthor(Author author){
        authorList.add(author);
    }
    public static Person getUserByUsername(String username){
        return usersList.stream().filter(p -> p.getUsername().equals(username)).findAny().orElse(null);
    }
    public static  Book getBookByTitle(String title){
        return bookList.stream().filter(b -> b.getTitle().equals(title)).findAny().orElse(null);
    }
    public static boolean CheckAuthorUniqneness(String username){
        return authorList.stream().filter(author -> author.getUsername().equals(username) ).findFirst().orElse(null) != null;
    }
    public static boolean CheckBookUniqneness(String bookname){
        return bookList.stream().filter(book -> book.getUsername().equals(bookname) ).findFirst().orElse(null) != null;
    }

}
