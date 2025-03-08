package Business.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Book {

    private String title;
    private String author;
    private String publisher;
    private int year;
    private long price;
    private String synopsis;
    private String content;
    private List<String> genres;

    public Book() {
    }
    public Book(String title, String author, String publisher, int year, long price, String synopsis, String content, List<String> genres) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.price = price;
        this.synopsis = synopsis;
        this.content = content;
        this.genres = genres;
    }
}