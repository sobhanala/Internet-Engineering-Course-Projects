package RoomTest;

import Exeptions.ValidationException;
import org.example.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

;

class RoomTest {

    @Test
    void testSetCapacity_ValidCapacity() {
        // Arrange
        Room room = new Room();

        // Act
        room.setCapacity(2);

        // Assert
        assertEquals(2, room.getCapacity());
    }

    @Test
    void testSetCapacity_InvalidCapacity_ThrowsException() {
        // Arrange
        Room room = new Room();

        // Act & Assert
        assertThrows(ValidationException.class, () -> room.setCapacity(0));
    }

    @Test
    void testSetRoomNum_ValidRoomNum() {
        // Arrange
        Room room = new Room();

        // Act
        room.setRoomNum(101);

        // Assert
        assertEquals(101, room.getRoomNum());
    }

    @Test
    void testSetRoomNum_InvalidRoomNum_ThrowsException() {
        // Arrange
        Room room = new Room();

        // Act & Assert
        assertThrows(ValidationException.class, () -> room.setRoomNum(0));
    }
}