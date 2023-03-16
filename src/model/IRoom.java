package model;

public interface IRoom {
    String getRoomNumber();

    RoomType getRoomType();

    Double getRoomPrice();

    // OK seriously how weird is it that we NEVER use isFree?
    boolean isFree();
}
