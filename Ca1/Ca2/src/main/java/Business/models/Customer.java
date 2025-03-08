package Business.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString(callSuper = true)
public class Customer extends Person {
    private List<BorrowedBook> borrowedBooks = new ArrayList<>();
    private List<Book> boughtBooks = new ArrayList<>();
    private Cart cart;
    private long walletBalance;
    @Getter
    private List<PurchaseRecord> purchaseHistory = new ArrayList<>(); // Added to track history

    public Customer(String username, String password, String email, Address address) {
        super(username, password, email, address);
        this.cart = new Cart();
        this.walletBalance = 0;
    }

    public void addToCart(Book book) {
        cart.addToCart(book);
    }

    public void addBorrow(Book book, int days) {
        cart.addBorrowedBook(book, days);
    }

    public void removeFromCart(Book book) {
        cart.removeFromCart(book);
    }

    public void addCredit(long credit) {
        this.walletBalance += credit;
    }

    public long getCartTotalPrice() {
        if (cart.getBooks().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
        long total = 0;
        for (Book book : cart.getBooks()) {
            if (cart.isBorrowed(book)) {
                int days = cart.getBorrowDays(book);
                total += (long) ((days / 10.0) * book.getPrice()); // Partial cost for borrowed books
            } else {
                total +=  book.getPrice(); // Full price for regular purchase
            }
        }
        return total;
    }

    public void finalisePurchase() {
        LocalDateTime now = LocalDateTime.now();
        List<PurchaseItem> items = new ArrayList<>();

        long totalCost = getCartTotalPrice();
        if (walletBalance-totalCost<0){
            throw new IllegalArgumentException("not found");
        }
        walletBalance-= totalCost;

        for (Book book : cart.getBooks()) {
            boolean isBorrowed = cart.isBorrowed(book);
            int borrowDays = isBorrowed ? cart.getBorrowDays(book) : 0;
            double finalPrice = isBorrowed ? (borrowDays / 10.0) * book.getPrice() : book.getPrice();
            items.add(new PurchaseItem(book, isBorrowed, borrowDays, finalPrice));

            if (isBorrowed) {
                LocalDateTime expiration = now.plusDays(borrowDays);
                borrowedBooks.add(new BorrowedBook(book, expiration));
            } else {
                boughtBooks.add(book);
            }
        }


        purchaseHistory.add(new PurchaseRecord(now, items, totalCost));

        cart.clearBooks();
    }

    public boolean canAccessBook(Book book) {
        cleanExpiredBooks();
        return borrowedBooks.stream()
                .anyMatch(b -> b.book().equals(book));
    }

    public void cleanExpiredBooks() {
        LocalDateTime now = LocalDateTime.now();
        borrowedBooks.removeIf(borrowed -> now.isAfter(borrowed.expiration()));
    }

    public Optional<Book> findAvailableBookByTitle(String bookTitle) {
        cleanExpiredBooks();
        List<Book> allBooks = new ArrayList<>();
        allBooks.addAll(boughtBooks);
        allBooks.addAll(borrowedBooks.stream().map(BorrowedBook::book).collect(Collectors.toList()));
        return allBooks.stream()
                .filter(book -> book.getTitle().equals(bookTitle))
                .findAny();
    }

}