package Business.models;

import Business.exeptions.ShopException;
import lombok.Getter;

import java.time.LocalDateTime;

import static Business.exeptions.ShopException.ErrorType.INVALID_DATA;

@Getter
public class Review {
    private final Person person;
    private final Book book;
    private final int rate;
    private final String comment;
    private final LocalDateTime timestamp;

    public Review(Person person, Book book, int rate, String comment) {
        this.person = person;
        this.book = book;
        if (rate < 1 || rate > 5) {
            throw new ShopException(INVALID_DATA,"Invalid rate: Must be between 1 and 5");
        }
        this.rate = rate;
        this.comment = comment;
        this.timestamp = LocalDateTime.now();
    }


}