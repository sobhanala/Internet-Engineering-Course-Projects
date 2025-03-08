package Business.models;

public record BorrowDetails(Book book, int days, boolean isBorrowed, long finalPrice) {
    BorrowDetails(Book book, int days) {
        this(book, days, days > 0,0);
    }
}


