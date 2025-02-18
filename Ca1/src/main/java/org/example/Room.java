package org.example;

import Exeptions.ValidationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Room {

    @JsonProperty("capacity")
    private int Capacity;

    @JsonProperty("roomNum")
    private int RoomNum;

    @JsonCreator
    public Room(
            @JsonProperty("capacity") int capacity,
            @JsonProperty("roomNum") int roomNum) {
        setCapacity(capacity);
        setRoomNum(roomNum);
    }

    public Room() {
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new ValidationException("Capacity must be a positive integer.");
        }
        this.Capacity = capacity;
    }

    public void setRoomNum(int roomNum) {
        if (roomNum <= 0) {
            throw new ValidationException("Room number must be a positive integer.");
        }
        this.RoomNum = roomNum;
    }
}
