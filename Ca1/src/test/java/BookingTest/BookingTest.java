package BookingTest;

import Exeptions.BookingException;
import Exeptions.InvalidDateException;
import org.example.Booking;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookingTest {

    @Test
    void testSetId_ValidId() {
        // Arrange
        Booking booking = new Booking();

        // Act
        booking.setId(1);

        // Assert
        assertEquals(1, booking.getId());
    }

    @Test
    void testSetId_InvalidId_ThrowsException() {
        // Arrange
        Booking booking = new Booking();

        // Act & Assert
        assertThrows(BookingException.class, () -> booking.setId(0));
    }

    @Test
    void testSetCustomerId_ValidId() {
        // Arrange
        Booking booking = new Booking();

        // Act
        booking.setCustomerId(1);

        // Assert
        assertEquals(1, booking.getCustomerId());
    }

    @Test
    void testSetCustomerId_InvalidId_ThrowsException() {
        // Arrange
        Booking booking = new Booking();

        // Act & Assert
        assertThrows(BookingException.class, () -> booking.setCustomerId(0));
    }

    @Test
    void testSetRoomId_ValidId() {
        // Arrange
        Booking booking = new Booking();

        // Act
        booking.setRoomId(1);

        // Assert
        assertEquals(1, booking.getRoomId());
    }

    @Test
    void testSetRoomId_InvalidId_ThrowsException() {
        // Arrange
        Booking booking = new Booking();

        // Act & Assert
        assertThrows(BookingException.class, () -> booking.setRoomId(0));
    }

    @Test
    void testSetFirstDate_ValidDate() {
        // Arrange
        Booking booking = new Booking();
        LocalDateTime now = LocalDateTime.now();

        // Act
        booking.setFirstDate(now);

        // Assert
        assertEquals(now, booking.getFirstDate());
    }

    @Test
    void testSetFirstDate_NullDate_ThrowsException() {
        // Arrange
        Booking booking = new Booking();

        // Act & Assert
        assertThrows(BookingException.class, () -> booking.setFirstDate(null));
    }

    @Test
    void testSetSecondDate_ValidDate() {
        // Arrange
        Booking booking = new Booking();
        LocalDateTime now = LocalDateTime.now();
        booking.setFirstDate(now);

        // Act
        booking.setSecondDate(now.plusDays(1));

        // Assert
        assertEquals(now.plusDays(1), booking.getSecondDate());
    }

    @Test
    void testSetSecondDate_NullDate_ThrowsException() {
        // Arrange
        Booking booking = new Booking();

        // Act & Assert
        assertThrows(BookingException.class, () -> booking.setSecondDate(null));
    }

    @Test
    void testSetSecondDate_BeforeFirstDate_ThrowsException() {
        // Arrange
        Booking booking = new Booking();
        LocalDateTime now = LocalDateTime.now();
        booking.setFirstDate(now);

        // Act & Assert
        assertThrows(InvalidDateException.class, () -> booking.setSecondDate(now.minusDays(1)));
    }

    @Test
    void testGetStayDurationInDays() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Booking booking = new Booking(1, 1, 1, now, now.plusDays(3));

        // Act
        int duration = booking.getStayDurationInDays();

        // Assert
        assertEquals(3, duration);
    }
}