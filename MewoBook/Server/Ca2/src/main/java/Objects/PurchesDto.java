package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PurchesDto {
    @JsonProperty("username")
    private String username;
}
