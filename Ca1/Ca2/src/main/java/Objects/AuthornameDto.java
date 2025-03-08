package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AuthornameDto {
    @JsonProperty("name")
    private String name;
}
