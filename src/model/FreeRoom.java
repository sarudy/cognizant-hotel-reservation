package model;

public class FreeRoom extends Room {
    /* pretty sure I don't need to give the price two decimals but it probably won't hurt anything and reminds me it's about money */
    public FreeRoom(final String roomNumber, final RoomType roomType, double roomPrice) {
        super(roomNumber, roomType, roomPrice);
        roomPrice = 0.00;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
