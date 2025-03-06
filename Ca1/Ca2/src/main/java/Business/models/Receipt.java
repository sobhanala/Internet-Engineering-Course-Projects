package Business.models;

import java.util.Date;

public class Receipt {
    public int bookCount;
    public double totalCost;
    public Date date;
    public Receipt(int bookCount, double totalCost, Date date) {
        this.bookCount = bookCount;
        this.totalCost = totalCost;
        this.date = date;
    }

}
