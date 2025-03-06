package Business.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Customer extends Person {
    private Cart cart=new Cart();

    public Customer(String username, String password, String email, Address address) {
        super(username, password, email, address);
    }
    public void AddToCart(Book book){
        cart.AddToCart(book);
    }
    public void RemoveFromCart(Book book){
        cart.RemoveFromCart(book);
    }



}
