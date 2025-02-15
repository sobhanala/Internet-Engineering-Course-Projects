package org.example;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Getter
@Setter
public class Booking {
    private UUID ReserveNum;
    private  Customer customer;
    private Room room;

    private Timestamp FirstDate;
    private Timestamp SecondDate;


    public int  getStayDurationInDays(){
        if (FirstDate == null || SecondDate == null) {
            throw new IllegalStateException("Both FirstDate and SecondDate must be set to calculate the stay duration.");
        }

        long diffInMilliseconds = SecondDate.getTime() - FirstDate.getTime();
        return (int) TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
    }



}
