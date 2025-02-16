package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    @JsonProperty("customers")

    List<Customer> customers;
    @JsonProperty("rooms")

    List<Room> rooms;

    @JsonProperty("bookings")

    List<Booking> bookings;


    public Hotel(List<Customer> customers, List<Room> rooms, List<Booking> bookings) {
        this.customers = customers;
        this.rooms = rooms;
        this.bookings = bookings;
    }

    public List<Room> getRooms(int minCap) {
       return rooms.stream().filter(room -> room.getCapacity()>minCap).collect(Collectors.toList());
    }
    public String getOldestCustomerName(){
        return customers.stream()
                .max(Comparator.comparingInt(Customer::getAge))
                .map(Customer::getName)
                .orElse("");
    }

    public List<String> getCustomersPhonesByRoomNumber(int roomNumber){
        List<String> phoneNumbers = null;
        for (Booking booking:bookings){
            if (booking.getRoom().getRoomNum()==roomNumber) {
                phoneNumbers.add(booking.getCustomer().getPhoneNumber());
            }
        }
        return phoneNumbers;
    }
}
