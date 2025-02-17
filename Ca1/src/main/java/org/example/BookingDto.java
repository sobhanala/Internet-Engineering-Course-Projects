package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record BookingDto(
        int id,
        Customer customer,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime checkInDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime checkOutDate
) {}
