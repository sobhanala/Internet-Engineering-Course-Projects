package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter

public class BookTitleDto {
    @JsonProperty("title")
    private  String title;
}
