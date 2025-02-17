package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Click\\IE\\Ca1\\src\\main\\java\\org\\example\\hotel_data.json";
        try {
            Hotel hotelData = JsonReader.readFromFile(filePath, Hotel.class);

            System.out.println("Customers: " + hotelData.getCustomers());
            System.out.println("Rooms: " + hotelData.getRooms());
            System.out.println("Bookings: " + hotelData.getBookings());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
