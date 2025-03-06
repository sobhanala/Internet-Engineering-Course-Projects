package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartDto {


    @JsonProperty("username")
    private String username;

    @JsonProperty("title")
    private String title;

    public CartDto() {
    }



}
