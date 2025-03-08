package Business.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Receipt {
    public int bookCount;
    public long totalCost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")

    public Date date;
    public Receipt(int bookCount, long totalCost, Date date) {
        this.bookCount = bookCount;
        this.totalCost = totalCost;
        this.date = date;
    }

}
