package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

import static service.ReservationService.printAllReservation;
import static service.ReservationService.rooms;

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

    public static void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            ReservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations() {
        printAllReservation();
    }
}
