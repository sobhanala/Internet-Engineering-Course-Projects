package Business.models;

import Business.exeptions.ShopException;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@ToString
public class Person {

    protected String username;
    protected String password;
    protected String email;
    protected Address address;

    protected static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");
    protected static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Person(String username, String password, String email, Address address) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setAddress(address);
    }

    private void setUsername(String username) {
        if (username == null || username.isEmpty() || !USERNAME_PATTERN.matcher(username).matches()) {
            throw new ShopException(ShopException.ErrorType.INVALID_USERNAME);

        }

        this.username = username;
    }

    private void setPassword(String password) {
        if (password == null || password.length() < 4) {
            throw new ShopException(ShopException.ErrorType.INVALID_PASSWORD);
        }
        this.password = password;
    }

    private void setEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ShopException(ShopException.ErrorType.INVALID_EMAIL);
        }
        this.email = email;
    }

    private void setAddress(Address address) {
        if (address == null || address.getCountry() == null || address.getCity() == null) {
            throw new ShopException(ShopException.ErrorType.INVALID_ADDRESS);
        }
        this.address = address;
    }


}