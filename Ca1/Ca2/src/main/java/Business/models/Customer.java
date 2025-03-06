package Business.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Customer extends Person {
    private Cart cart;
    private long walletBalance;
    public Customer(String username, String password, String email, Address address) {
        super(username, password, email, address);
        this.cart = new Cart();
        this.walletBalance=0;
    }
    public void AddToCart(Book book){
        cart.AddToCart(book);
    }
    public void RemoveFromCart(Book book){
        cart.RemoveFromCart(book);
    }

    public void AddCredit(long credit){
        this.walletBalance+=credit;
        }

    public double PurchaseCart(){
        if (!cart.books.isEmpty()){
            long total=0;
            for (Book book:cart.books){
                total+= (long) book.getPrice();
            }
            if (walletBalance>=total){
                walletBalance-=total;
                return total;
            }
            else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        }
        else {
            throw new IllegalArgumentException("Cart is empty");
        }
    }
    public void EmptyCart(){
        cart.books.clear();
    }
}
