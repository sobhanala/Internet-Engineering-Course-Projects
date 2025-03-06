package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDto {

    @JsonProperty("city")
    private  String City;
    @JsonProperty("country")
    private  String Country;


    public AddressDto() {
    }
}
