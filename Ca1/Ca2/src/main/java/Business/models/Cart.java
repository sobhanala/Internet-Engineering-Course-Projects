package Business.models;

import lombok.Getter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Cart {
    private final List<Book> books = new ArrayList<>();
    private final List<BorrowDetails> borrowDetails = new ArrayList<>();

    public void addToCart(Book book) {
        books.add(book);
        borrowDetails.add(new BorrowDetails(book, 0)); // 0 days = regular purchase
    }

    public void addBorrowedBook(Book book, int days) {
        books.add(book);
        borrowDetails.add(new BorrowDetails(book, days));
    }

    public void removeFromCart(Book book) {
        int index = books.indexOf(book);
        if (index != -1) {
            books.remove(index);
            borrowDetails.remove(index);
        }
    }

    public boolean isBorrowed(Book book) {
        int index = books.indexOf(book);
        return index != -1 && borrowDetails.get(index).days() > 0;
    }

    public int getBorrowDays(Book book) {
        int index = books.indexOf(book);
        return index != -1 ? borrowDetails.get(index).days() : 0;
    }

    public void clearBooks() {
        books.clear();
        borrowDetails.clear();
    }
}

