package Business.models;

import Business.exeptions.ShopException;
import lombok.Getter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Cart {
    private final List<Book> books = new ArrayList<>();
    private final List<BorrowDetails> borrowDetails = new ArrayList<>();
    private static final int MAX_ITEMS = 10;
    public void addToCart(Book book) {
        if (books.size() >= MAX_ITEMS) {
            throw new ShopException(ShopException.ErrorType.CART_FULL);
        }
        books.add(book);
        borrowDetails.add(new BorrowDetails(book, 0,false,book.getPrice()));
    }

    public void addBorrowedBook(Book book, int days) {
        if (books.size() >= MAX_ITEMS) {
            throw new ShopException(ShopException.ErrorType.CART_FULL);
        }
        books.add(book);
        borrowDetails.add(new BorrowDetails(book, days,true,days*book.getPrice()));
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

