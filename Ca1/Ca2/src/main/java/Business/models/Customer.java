package Business.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class Customer extends Person {

    private List<BorrowedBook> borrowedBooks = new ArrayList<>();
    private List<Book> boughtBooks = new ArrayList<>();
    private Cart cart;
    private long walletBalance;
    public Customer(String username, String password, String email, Address address) {
        super(username, password, email, address);
        this.cart = new Cart();
        this.walletBalance=0;
    }
    public void addToCart(Book book){
        cart.addToCart(book);
    }
    public void addBorrow(Book book,int days){
        cart.addBorrowedBook(book,days);
    }
    public void removeFromCart(Book book){
        cart.removeFromCart(book);
    }

    public void addCredit(long credit){
        this.walletBalance+=credit;
        }

    public double purchaseCart() {
        if (cart.getBooks().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
        double total = 0;
        for (Book book : cart.getBooks()) {
            if (cart.isBorrowed(book)) {
                int days = cart.getBorrowDays(book);
                total += (days / 10.0) * book.getPrice(); // Partial cost for borrowed books
            } else {
                total += book.getPrice(); // Full price for regular purchase
            }
        }
        if (walletBalance < total) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        walletBalance -= total;

        return total;
    }
    public void finalisePurchase(){
        boughtBooks.addAll(cart.getBooks());
        LocalDateTime now = LocalDateTime.now();
        for (Book book : cart.getBooks()) {
            if (cart.isBorrowed(book)) {
                int days = cart.getBorrowDays(book);
                LocalDateTime expiration = now.plusDays(days);
                borrowedBooks.add(new BorrowedBook(book, expiration));
            }
        }
        cart.clearBooks();

    }
    public boolean canAccessBook(Book book) {
        cleanExpiredBooks();
        return borrowedBooks.stream()
                .anyMatch(b -> b.book().equals(book));
    }

    private void cleanExpiredBooks() {
        LocalDateTime now = LocalDateTime.now();
        borrowedBooks.removeIf(borrowed -> now.isAfter(borrowed.expiration()));
    }
}
