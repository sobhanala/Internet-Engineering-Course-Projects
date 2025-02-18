package org.example;

import Exeptions.BookingException;
import Exeptions.InvalidDateException;
import com.fasterxml.jackson.annotation.JsonCreator;
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
    private int Id;

    @JsonProperty("customerId")
    private long CustomerId;

    @JsonProperty("roomId")
    private int RoomId;

    @JsonProperty("firstDate")
    private LocalDateTime FirstDate;

    @JsonProperty("secondDate")
    private LocalDateTime SecondDate;

    private Customer customer;
    private Room room;

    @JsonCreator
    public Booking(
            @JsonProperty("id") int id,
            @JsonProperty("customerId") long customerId,
            @JsonProperty("roomId") int roomId,
            @JsonProperty("firstDate") LocalDateTime firstDate,
            @JsonProperty("secondDate") LocalDateTime secondDate) {
        setId(id);
        setCustomerId(customerId);
        setRoomId(roomId);
        setFirstDate(firstDate);
        setSecondDate(secondDate);
    }

    public Booking() {
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new BookingException("ID must be a positive integer.");
        }
        this.Id = id;
    }

    public void setCustomerId(long customerId) {
        if (customerId <= 0) {
            throw new BookingException("Customer ID must be a positive integer.");
        }
        this.CustomerId = customerId;
    }

    public void setRoomId(int roomId) {
        if (roomId <= 0) {
            throw new BookingException("Room ID must be a positive integer.");
        }
        this.RoomId = roomId;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        if (firstDate == null) {
            throw new BookingException("Check-in date cannot be null.");
        }
        this.FirstDate = firstDate;
    }

    public void setSecondDate(LocalDateTime secondDate) {
        if (secondDate == null) {
            throw new BookingException("Check-out date cannot be null.");
        }
        if (FirstDate != null && secondDate.isBefore(FirstDate)) {
            throw new InvalidDateException("Check-out date must be after check-in date.", FirstDate, secondDate);
        }
        this.SecondDate = secondDate;
    }

    public int getStayDurationInDays() {
        return (int) Duration.between(FirstDate, SecondDate).toDays();
    }
}
