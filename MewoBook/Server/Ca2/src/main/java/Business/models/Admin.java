package Business.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Admin extends Person {


    public Admin(String username, String password, String email, Address address) {
        super(username, password, email, address);
    }
}