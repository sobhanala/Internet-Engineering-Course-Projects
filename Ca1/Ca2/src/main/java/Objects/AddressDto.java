package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDto {

    @JsonProperty("city")
    public String City;
    @JsonProperty("country")
    public String Country;


    public AddressDto() {
    }
}
