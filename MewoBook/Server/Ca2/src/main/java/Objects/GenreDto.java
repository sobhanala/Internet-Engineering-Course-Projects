package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GenreDto {
    @JsonProperty("genre")
    private String genre;

}
