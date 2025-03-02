package Business.models;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class DataLoader {
    public static final List<Book> bookList = new ArrayList<>();
    public static final List<Person> usersList = new ArrayList<>();
    public static final List<Author> authorList = new ArrayList<>();

    private DataLoader() {
        throw new UnsupportedOperationException("DataLoader is a static utility class and cannot be instantiated.");
    }
    static {
        bookList.add(new Book());
        usersList.add(new Person());
        authorList.add(new Author());
    }
}
