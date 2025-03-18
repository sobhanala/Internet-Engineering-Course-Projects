package Business.models;

import lombok.Getter;

@Getter
public final class UserDetails {
    private final String username;
    private final String role;
    private final String email;
    private final Address address;
    private  long balance;

    public UserDetails(String username,
                       String role,
                       String email,
                       Address address
    ) {
        this.username = username;
        this.role = role;
        this.email = email;
        this.address = address;
    }
    public UserDetails(String username,
                       String role,
                       String email,
                       Address address,
                       long balance
    ) {
        this.username = username;
        this.role = role;
        this.email = email;
        this.address = address;
        this.balance=balance;
    }

}
