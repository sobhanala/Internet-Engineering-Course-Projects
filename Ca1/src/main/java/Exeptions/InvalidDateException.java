package Exeptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InvalidDateException extends BookingException {
    private final LocalDateTime firstDate;
    private final LocalDateTime secondDate;

    public InvalidDateException(String message, LocalDateTime firstDate, LocalDateTime secondDate) {
        super(message, "INVALID_DATE");
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

}