package Objects;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter

public class UsernameDto {
    @JsonProperty("username")
    private  String username;
}
