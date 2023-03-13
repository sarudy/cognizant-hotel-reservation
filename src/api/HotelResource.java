package api;


import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import static service.CustomerService.customers;
import static service.ReservationService.rooms;

public class HotelResource {
    private static HotelResource INSTANCE;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HotelResource();
        }
        return INSTANCE;
    }

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, LocalDate CheckInDate, LocalDate CheckOutDate) {
        return ReservationService.reserveARoom(getCustomer(customerEmail), room, CheckInDate, CheckOutDate);
    }

    public static Collection<Reservation> getCustomerReservations(String customerEmail) {
        return ReservationService.getCustomersReservation(customers.get(customerEmail));
    }

    public static Map<String, IRoom> findARoom(LocalDate checkIn, LocalDate checkOut) {
        return ReservationService.findRooms(checkIn, checkOut);
    }
}
