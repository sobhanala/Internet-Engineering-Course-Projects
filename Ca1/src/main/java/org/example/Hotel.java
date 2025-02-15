package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    List<Customer> customers;
    List<Room> rooms;
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
        return  customers.stream().
    }
}
