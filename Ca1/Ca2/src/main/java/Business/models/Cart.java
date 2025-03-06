package Business.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cart {
    private List<Book> books;
    public Cart() {
    }
    public void AddToCart(Book book) {
        if (books.size()>=10){
            books.add(book);
        }
        else {
            throw new IllegalArgumentException("Cart is full");
        }

    }



}
