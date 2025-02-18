package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class Hotel {

    @JsonProperty("customers")
    private List<Customer> customers;

    @JsonProperty("rooms")
    private List<Room> rooms;

    @JsonProperty("bookings")
    private List<Booking> bookings;

    @JsonCreator
    public Hotel(
            @JsonProperty("customers") List<Customer> customers,
            @JsonProperty("rooms") List<Room> rooms,
            @JsonProperty("bookings") List<Booking> bookings) {
        setCustomers(customers);
        setRooms(rooms);
        setBookings(bookings);
        mapBookings();
    }

    public Hotel() {
        this.customers = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    @JsonProperty("customers")
    public void setCustomers(List<Customer> customers) {

        this.customers = customers;
    }

    @JsonProperty("rooms")
    public void setRooms(List<Room> rooms) {

        this.rooms = rooms;
    }

    @JsonProperty("bookings")
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
        mapBookings();
    }

    public List<Room> getRooms(int minCap) {
        return rooms.stream()
                .filter(room -> room.getCapacity() > minCap)
                .collect(Collectors.toList());
    }

    public String getOldestCustomerName() {
        return customers.stream()
                .max(Comparator.comparingInt(Customer::getAge))
                .map(Customer::getName)
                .orElse("");
    }

    public List<String> getCustomersPhonesByRoomNumber(int roomNumber) {
        return bookings.stream()
                .filter(b -> b.getRoomId() == roomNumber)
                .map(b -> customers.stream()
                        .filter(c -> c.getSsn().equals(String.valueOf(b.getCustomerId())))
                        .findFirst()
                        .map(Customer::getPhoneNumber)
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private void mapBookings() {
        if (bookings == null || customers == null || rooms == null) return;

        for (Booking booking : bookings) {
            booking.setCustomer(customers.stream()
                    .filter(c -> c.getSsn().equals(String.valueOf(booking.getCustomerId())))
                    .findFirst()
                    .orElse(null));

            booking.setRoom(rooms.stream()
                    .filter(r -> r.getRoomNum() == booking.getRoomId())
                    .findFirst()
                    .orElse(null));
        }
    }

    public void logState(String filePath) throws IOException {
        List<OutPutHotel> tempRooms = new ArrayList<>();
        for (Room room : rooms) {
            OutPutHotel outPutHotel = new OutPutHotel();
            outPutHotel.capacity = room.getCapacity();
            outPutHotel.roomId = room.getRoomNum();
            outPutHotel.bookings = bookings.stream()
                    .filter(booking -> booking.getRoomId() == room.getRoomNum())
                    .map(booking -> new BookingDto(
                            booking.getId(),
                            booking.getCustomer(),
                            booking.getFirstDate(),
                            booking.getSecondDate()))
                    .collect(Collectors.toList());
            tempRooms.add(outPutHotel);
        }
        JsonReader.writeToFile(filePath, tempRooms);
    }
}
