package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddReviewDto {
        @JsonProperty("username")
        private String username;

        @JsonProperty("title")
        private String title;

        @JsonProperty("rate")
        private int rate;

        @JsonProperty("comment")
        private String comment;
}
