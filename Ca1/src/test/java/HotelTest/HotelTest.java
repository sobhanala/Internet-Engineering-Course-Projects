package HotelTest;

import org.example.Booking;
import org.example.Customer;
import org.example.Hotel;
import org.example.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;
    private List<Customer> customers;
    private List<Room> rooms;
    private List<Booking> bookings;

    @BeforeEach
    void setUp() {
        customers = new ArrayList<>();
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();

        customers.add(new Customer("1234567890", "+1234567890", "John Doe", 30));
        customers.add(new Customer("1111111111", "+0987654321", "Jane Doe", 25));

        rooms.add(new Room(2, 101));
        rooms.add(new Room(4, 102));

        bookings.add(new Booking(1, 1234567890, 101, LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        bookings.add(new Booking(2, 1111111111, 102, LocalDateTime.now(), LocalDateTime.now().plusDays(3)));

        hotel = new Hotel(customers, rooms, bookings);
    }

    @Test
    void testGetRooms_FilterByCapacity() {
        // Act
        List<Room> filteredRooms = hotel.getRooms(1);

        // Assert
        assertEquals(2, filteredRooms.size());
    }

    @Test
    void testGetRooms_FilterByHighCapacity() {
        // Act
        List<Room> filteredRooms = hotel.getRooms(3);

        // Assert
        assertEquals(1, filteredRooms.size());
        assertEquals(102, filteredRooms.get(0).getRoomNum());
    }

    @Test
    void testGetOldestCustomerName() {
        // Act
        String oldestCustomerName = hotel.getOldestCustomerName();

        // Assert
        assertEquals("John Doe", oldestCustomerName);
    }

    @Test
    void testGetOldestCustomerName_EmptyCustomers() {
        // Arrange
        hotel.setCustomers(new ArrayList<>());

        // Act
        String oldestCustomerName = hotel.getOldestCustomerName();

        // Assert
        assertEquals("", oldestCustomerName);
    }

    @Test
    void testGetCustomersPhonesByRoomNumber() {
        // Act
        List<String> phones = hotel.getCustomersPhonesByRoomNumber(101);

        // Assert
        assertEquals(1, phones.size());
        assertEquals("+1234567890", phones.get(0));
    }

    @Test
    void testGetCustomersPhonesByRoomNumber_InvalidRoomNumber() {
        // Act
        List<String> phones = hotel.getCustomersPhonesByRoomNumber(999);

        // Assert
        assertTrue(phones.isEmpty());
    }


    @Test
    void testMapBookings_CustomerAndRoomMapping() {
        // Act
        Booking booking = hotel.getBookings().get(0);

        // Assert
        assertNotNull(booking.getCustomer());
        assertNotNull(booking.getRoom());
        assertEquals("John Doe", booking.getCustomer().getName());
        assertEquals(101, booking.getRoom().getRoomNum());
    }

    @Test
    void testMapBookings_InvalidCustomerId() {
        // Arrange
        bookings.add(new Booking(3, 999999999, 101, LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        hotel.setBookings(bookings);

        // Act
        Booking booking = hotel.getBookings().get(2);

        // Assert
        assertNull(booking.getCustomer());
    }

    @Test
    void testMapBookings_InvalidRoomId() {
        // Arrange
        bookings.add(new Booking(3, 1234567890, 999, LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        hotel.setBookings(bookings);

        // Act
        Booking booking = hotel.getBookings().get(2);

        // Assert
        assertNull(booking.getRoom());
    }

    @Test
    void testLogState_NoExceptionThrown() {
        // Act & Assert
        assertDoesNotThrow(() -> hotel.logState("hotel_state.json"));
    }

    @Test
    void testLogState_InvalidFilePath_ThrowsException() {
        // Act & Assert
        assertThrows(IOException.class, () -> hotel.logState("/invalid/path/hotel_state.json"));
    }

    @Test
    void testLogState_ValidFilePath() {
        // Arrange
        String filePath = "hotel_state.json";

        assertDoesNotThrow(() -> hotel.logState(filePath));

        Path path = Paths.get(filePath);
        assertTrue(Files.exists(path));

        try {
            String content = Files.readString(path);


            String trimmedContent = content.replaceAll("\\s", "");

            assertTrue(trimmedContent.contains("\"roomId\":101"), "roomId:101 not found in JSON");
            assertTrue(trimmedContent.contains("\"roomId\":102"), "roomId:102 not found in JSON");

            assertTrue(trimmedContent.contains("\"ssn\":\"1234567890\""), "ssn:1234567890 not found in JSON");
            assertTrue(trimmedContent.contains("\"ssn\":\"1111111111\""), "ssn:1111111111 not found in JSON");

            assertTrue(trimmedContent.contains("\"phoneNumber\":\"+1234567890\""), "phoneNumber:+1234567890 not found in JSON");
            assertTrue(trimmedContent.contains("\"phoneNumber\":\"+0987654321\""), "phoneNumber:+0987654321 not found in JSON");

            assertTrue(trimmedContent.contains("\"name\":\"JohnDoe\""), "name:John Doe not found in JSON");
            assertTrue(trimmedContent.contains("\"name\":\"JaneDoe\""), "name:Jane Doe not found in JSON");

            assertTrue(trimmedContent.contains("\"age\":30"), "age:30 not found in JSON");
            assertTrue(trimmedContent.contains("\"age\":25"), "age:25 not found in JSON");

            assertTrue(trimmedContent.contains("\"checkInDate\":"), "checkInDate not found in JSON");
            assertTrue(trimmedContent.contains("\"checkOutDate\":"), "checkOutDate not found in JSON");
        } catch (IOException e) {
            fail("Failed to read the file: " + e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                fail("Failed to delete the test file: " + e.getMessage());
            }
        }
    }
}
