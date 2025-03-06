package Business.models;

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
    @Getter
    protected double walletBalance;

    protected static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");
    protected static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Person() {
        this.walletBalance = 0.0;
    }

    public Person(String username, String password, String email, Address address) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setAddress(address);
        this.walletBalance = 0.0;
    }

    private void setUsername(String username) {
        if (username == null || username.isEmpty() || !USERNAME_PATTERN.matcher(username).matches()) {
            throw new IllegalArgumentException("Invalid username: Only letters, numbers, '-', and '_' are allowed.");
        }

        this.username = username;
    }

    private void setPassword(String password) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Invalid password: Must be at least 4 characters long.");
        }
        this.password = password;
    }

    private void setEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email: Must follow the format example@test.com.");
        }
        this.email = email;
    }

    private void setAddress(Address address) {
        if (address == null || address.getCountry() == null || address.getCity() == null) {
            throw new IllegalArgumentException("Invalid address: Country and city are required.");
        }
        this.address = address;
    }


}