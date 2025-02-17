package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class Room {
    @JsonProperty("capacity")
    private int Capacity;

    @JsonProperty("id")
    private int RoomNum;

    public Room(){

    }

    public Room(int cap, int roomNum) {
        if (cap <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer.");
        }
        if (roomNum <= 0) {
            throw new IllegalArgumentException("Room number must be a positive integer.");
        }

        this.Capacity = cap;
        this.RoomNum = roomNum;
    }



}