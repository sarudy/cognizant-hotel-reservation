import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.time.LocalDate;
import java.util.Scanner;

import static service.CustomerService.addCustomer;
import static service.ReservationService.findRooms;

public class Tester {
    public static void main(String[] args) {
// begin email format test
        // inline value test

        Customer goodCustomer = new Customer("Henry", "Jekyll", "drjekyll@valid.com");
//        System.out.println(goodCustomer);

//        Customer badCustomer = new Customer("Edward", "Hyde", "misterhydenowhere.com");
//        System.out.println(badCustomer);

        // user entered value test

//       try {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("\n Enter your first name:");
//            String userFirst = scanner.nextLine();
//            System.out.println("\n Enter your last name:");
//            String userLast = scanner.nextLine();
//            System.out.println("\n Enter your email. This will also be your customer id:");
//            String userEmail = CustomerService.getValidEmail(scanner.nextLine());
//            Customer enteredCustomer = new Customer(userFirst, userLast, userEmail);
//            System.out.println(enteredCustomer);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Please enter a valid email address.");
//        }
//  customer service test
        addCustomer("Levi", "King", "levi@king.com");
        addCustomer("Ryan", "Hill", "ryan@hill.com");
        addCustomer("Kris", "Hall", "kris@hall.com");
        addCustomer("Mary", "Diaz", "mary@diaz.com");
        addCustomer("Beth", "Cruz", "beth@cruz.com");
//        System.out.println(CustomerService.getAllCustomers());
//        System.out.println(CustomerService.getCustomer("kris@hall.com"));
//        System.out.println(CustomerService.getCustomer("bill@frye.com"));

        IRoom newRoom1 = new Room("100", RoomType.SINGLE, 200.00);
        IRoom newRoom2 = new Room("110", RoomType.SINGLE, 250.00);
        IRoom newRoom3 = new Room("200", RoomType.DOUBLE, 200.00);
        IRoom newRoom4 = new Room("205", RoomType.DOUBLE, 300.00);
        IRoom newRoom5 = new Room("208", RoomType.SINGLE, 1000.00);
        ReservationService.addRoom(newRoom1);
        ReservationService.addRoom(newRoom2);
        ReservationService.addRoom(newRoom3);
        ReservationService.addRoom(newRoom4);
        ReservationService.addRoom(newRoom5);
//        System.out.println(newRoom);
//        System.out.println(ReservationService.rooms);
// test make a reservation
        Customer pilotCustomer = new Customer("Amelia", "Earhart", "earhart@lostatsea.net");
        Customer cleverCustomer = new Customer("Irene", "Adler", "irene@bohemia.com");
        LocalDate in = LocalDate.of(2023, 10, 8);
        LocalDate out = LocalDate.of(2023, 10, 12);
//        Reservation newRes1 = new Reservation(goodCustomer, newRoom2, in, out);
//        System.out.println(newRes1);
        LocalDate in2 = LocalDate.of(2023, 11, 2);
        LocalDate out2 = LocalDate.of(2023, 11, 4);
        ReservationService.reserveARoom(goodCustomer, newRoom2, in, out);
        ReservationService.reserveARoom(goodCustomer, newRoom3, in2, out2);
//        System.out.println(ReservationService.reservations);
        ReservationService.reserveARoom(cleverCustomer, newRoom4, LocalDate.of(2023, 04, 2),
                LocalDate.of(2023, 04, 12));
//        System.out.println(ReservationService.findRooms(LocalDate.of(2023, 10, 9),
//                LocalDate.of(2023, 10, 12)));
//        System.out.println(ReservationService.getCustomersReservation(goodCustomer));
//        ReservationService.printAllReservation();
        api.HotelResource.createACustomer(cleverCustomer.getFirstName(), cleverCustomer.getLastName(), cleverCustomer.getEmail());
        api.HotelResource.createACustomer(goodCustomer.getFirstName(), goodCustomer.getLastName(), goodCustomer.getEmail());
        api.HotelResource.createACustomer(pilotCustomer.getFirstName(), pilotCustomer.getLastName(), pilotCustomer.getEmail());
//        System.out.println(HotelResource.getCustomer("irene@bohemia.com"));
        ReservationService.addRoom(newRoom1);
//        System.out.println(HotelResource.getRoom("202"));
//        System.out.println(HotelResource.bookARoom("irene@bohemia.com", newRoom4, LocalDate.of(2024, 03, 04),
//                LocalDate.of(2024, 03, 05)));
//        System.out.println("-----");
//        System.out.println((HotelResource.getCustomerReservations("irene@bohemia.com")));
//        System.out.println(HotelResource.findARoom(LocalDate.of(2023, 04, 03), LocalDate.of(2023, 04, 06)));
//        System.out.println("-----");
//        System.out.println(HotelResource.getCustomer("drjekyll@valid.com"));
//        System.out.println("~~~~~");
//        System.out.println(AdminResource.getAllRooms());
//        System.out.println(AdminResource.getAllCustomers());
//          AdminResource.displayAllReservations();
        MainMenu.startMainMenu();
    }

}