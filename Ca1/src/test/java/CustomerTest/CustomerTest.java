package CustomerTest;

import Exeptions.MissingRequiredFieldException;
import Exeptions.ValidationException;
import org.example.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {

    @Test
    void testSetSsn_ValidSsn() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.setSsn("1234567890");

        // Assert
        assertEquals("1234567890", customer.getSsn());
    }

    @Test
    void testSetSsn_NullSsn_ThrowsException() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertThrows(MissingRequiredFieldException.class, () -> customer.setSsn(null));
    }

    @Test
    void testSetSsn_InvalidSsn_ThrowsException() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertThrows(ValidationException.class, () -> customer.setSsn("12345"));
    }

    @Test
    void testSetPhoneNumber_ValidPhoneNumber() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.setPhoneNumber("+1234567890");

        // Assert
        assertEquals("+1234567890", customer.getPhoneNumber());
    }

    @Test
    void testSetPhoneNumber_NullPhoneNumber_ThrowsException() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertThrows(MissingRequiredFieldException.class, () -> customer.setPhoneNumber(null));
    }

    @Test
    void testSetPhoneNumber_InvalidPhoneNumber_ThrowsException() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertThrows(ValidationException.class, () -> customer.setPhoneNumber("123"));
    }

    @Test
    void testSetName_ValidName() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.setName("John Doe");

        // Assert
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void testSetName_NullName_ThrowsException() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertThrows(MissingRequiredFieldException.class, () -> customer.setName(null));
    }

    @Test
    void testSetName_InvalidName_ThrowsException() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertThrows(ValidationException.class, () -> customer.setName("A"));
    }

    @Test
    void testSetAge_ValidAge() {
        // Arrange
        Customer customer = new Customer();

        // Act
        customer.setAge(30);

        // Assert
        assertEquals(30, customer.getAge());
    }

    @Test
    void testSetAge_InvalidAge_ThrowsException() {
        // Arrange
        Customer customer = new Customer();

        // Act & Assert
        assertThrows(ValidationException.class, () -> customer.setAge(-1));
        assertThrows(ValidationException.class, () -> customer.setAge(121));
    }
}