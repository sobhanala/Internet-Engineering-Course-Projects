package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddCreditDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("credit")
    private long credit;

    public AddCreditDto() {
    }

}
