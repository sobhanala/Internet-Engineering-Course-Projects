package org.example;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Click\\IE\\Ca1\\src\\main\\java\\org\\example\\hotel_data.json";
        String filePath2 = "C:\\Users\\Click\\IE\\Ca1\\src\\main\\java\\org\\example\\hotel.json";

        try {
            Hotel hotelData = JsonReader.readFromFile(filePath, Hotel.class);

            System.out.println("Customers: " + hotelData.getCustomers());
            System.out.println("Rooms: " + hotelData.getRooms());
            System.out.println("Bookings: " + hotelData.getBookings());
            hotelData.logState(filePath2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
