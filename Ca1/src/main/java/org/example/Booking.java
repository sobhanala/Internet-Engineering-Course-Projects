package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Booking {

    @JsonProperty("id")
    private int id;
    @JsonProperty("customer_id")
    private  int customerId;

    @JsonProperty("room_id")
    private int roomId;

    @JsonProperty("check_in")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private LocalDateTime FirstDate;

    @JsonProperty("check_out")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime SecondDate;


    private Customer customer;
    private Room room;

    public Booking(){

    }
    public int  getStayDurationInDays(){
        if (FirstDate == null || SecondDate == null) {
            throw new IllegalStateException("Both FirstDate and SecondDate must be set to calculate the stay duration.");
        }

        return (int) Duration.between(FirstDate, SecondDate).toDays();
    }




}
