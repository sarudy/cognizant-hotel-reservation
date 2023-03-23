package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import static service.ReservationService.*;

public class AdminResource {
    private static AdminResource INSTANCE;

    private AdminResource() {
    }

    public static AdminResource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdminResource();
        }
        return INSTANCE;
    }

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(IRoom room) {
        ReservationService.addRoom(room);
    }

    public static void addAllRooms(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            ReservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public static void printAllRooms() {
        printRooms(rooms);
    }

    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations() {
        printAllReservation();
    }

    public static String getValidRoomNumber() {
        Scanner scanner = new Scanner(System.in);
        boolean goodNumber = false;
        // user input is guilty until proven innocent
        while (!goodNumber) {
            System.out.println("Enter a three digit room number:");
            // most hotels that are large enough not to have named rooms start first floor rooms at 100, second floor rooms
            // at 200 and so forth.  Though no upper bound was given, a hotel cannot have infinite height. For the sake of
            // simplicity, then, this is hotel has nine floors.
            String roomNumber = scanner.nextLine();
            try {
                int isANumber = Integer.parseInt(roomNumber);
                if (isANumber > 99 && isANumber < 1000) {
                    return roomNumber;
                } else {
                    System.out.println("The room number should be between 100 and 999.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Room numbers should be numbers only.");
                getValidRoomNumber();
            }
        }return null;
    }
}
