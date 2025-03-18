package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DateDto {
    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;


}
