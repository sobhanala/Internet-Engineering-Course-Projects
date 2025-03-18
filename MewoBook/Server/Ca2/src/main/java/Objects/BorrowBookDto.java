package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
public class BorrowBookDto {
    @JsonProperty("username")
    private String username;

    @JsonProperty("title")
    private String title;

    @JsonProperty("days")
    private int days;
}
