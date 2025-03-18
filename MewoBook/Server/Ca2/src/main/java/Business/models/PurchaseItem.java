package Business.models;

import java.util.List;

public record PurchaseItem(
        Book book,
        boolean isBorrowed,
        int borrowDays,
        double finalPrice
) {
    public String title() { return book.getTitle(); }
    public String author() { return book.getAuthor();}
    public String publisher() { return book.getPublisher(); }
    public List<String> genres() { return book.getGenres(); }
    public int year() { return book.getYear(); }
    public double price() { return book.getPrice(); }
}