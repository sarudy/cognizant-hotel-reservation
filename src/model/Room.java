package model;

import java.text.NumberFormat;
import java.util.Objects;

public class Room implements IRoom {
    private final String roomNumber;
    private final RoomType roomType;
    private final Double roomPrice;
    private final boolean isFree;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public Room(final String roomNumber, final RoomType roomType, final Double roomPrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        isFree = roomPrice == 0.00;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + ": " +
                roomType + ", " +
                formatter.format(roomPrice) + " per night";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return roomNumber.equals(room.roomNumber) && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, roomType);
    }
}
