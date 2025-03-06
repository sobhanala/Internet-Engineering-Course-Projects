package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BookDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("year")
    private int year;

    @JsonProperty("price")
    private long price;

    @JsonProperty("synopsis")
    private String synopsis;

    @JsonProperty("content")
    private String content;

    @JsonProperty("genres")
    private List<String> genres;

    public BookDto() {
    }
}
