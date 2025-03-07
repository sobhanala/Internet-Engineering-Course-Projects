package Business.models;

import java.util.Objects;

public final class UserDetails {
    private  String username;
    private  String role;
    private  String email;
    private  Address address;
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

    public String username() {
        return username;
    }

    public String role() {
        return role;
    }

    public String email() {
        return email;
    }

    public Address address() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserDetails) obj;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.role, that.role) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role, email, address);
    }

    @Override
    public String toString() {
        return "UserDetails[" +
                "username=" + username + ", " +
                "role=" + role + ", " +
                "email=" + email + ", " +
                "address=" + address + ']';
    }


}
