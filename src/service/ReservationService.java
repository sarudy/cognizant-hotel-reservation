package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ReservationService {
    public static final Map<String, IRoom> rooms = new HashMap<>();
    public static final List<Reservation> reservations = new ArrayList<Reservation>();
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();
    private static ReservationService INSTANCE;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }

    public static void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public static Reservation reserveARoom(final Customer customer, final IRoom room,
                                           final LocalDate checkInDate, final LocalDate checkOutDate) {
        final Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    private static boolean isBooked(final Reservation reservation,
                                    final LocalDate checkInDate, final LocalDate checkOutDate) {
        return checkOutDate.isBefore(reservation.getCheckOutDate()) &&
                checkOutDate.isAfter(reservation.getCheckInDate());
    }

    public static Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        // This will need a try catch to be sure the dates are valid,
        // in the future and checkout is at least one day after checkin
        // saving this for when the scanner input part is ready
        Collection<IRoom> booked = new ArrayList<>();
        Collection<IRoom> open = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (isBooked(reservation, checkInDate, checkOutDate)) {
                booked.add(reservation.getRoom());
            }
            if (!isBooked(reservation, checkInDate, checkOutDate)) {
                open.add(reservation.getRoom());
            }
        }
        return open;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> reservedByCustomer = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (customer == reservation.getCustomer()) {
                reservedByCustomer.add(reservation);
            }
        }
        return reservedByCustomer;
    }

    public static void printAReservation(Reservation reservation) {
        DateTimeFormatter dmy = DateTimeFormatter.ofPattern("d MMM YYYY");
        System.out.println(reservation.getCustomer() + "\n" +
                reservation.getRoom() + "\n" +
                "Dates: " + dmy.format(reservation.getCheckInDate()) + " to " + dmy.format(reservation.getCheckOutDate()) + "\n" +
                "Total Cost: " +
                formatter.format((ChronoUnit.DAYS.between(reservation.getCheckInDate(),
                        reservation.getCheckOutDate())) * reservation.getRoom().getRoomPrice()) + "\n"
        );

    }

    public static void printAllReservation() {
        final Collection<Reservation> allReservations = new LinkedList<>();
        if (reservations.isEmpty()) {
            System.out.println("No active reservations found.");
        } else {
            Collections.sort(reservations, Comparator.comparing(Reservation::getCheckInDate));
            for (Reservation reservation : reservations) {
                printAReservation(reservation);
            }
        }
    }

    public static boolean isADate(String date) {
        try {
            LocalDate test = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate getValiDate(String date) {
        {
            if (isADate(date)) {
                boolean realDate = false;
                while (!realDate) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please enter a valid date in the format yyyy-mm-dd");
                    String tryAgain = scanner.nextLine();
                    realDate = isADate(tryAgain);
                    if (isADate(tryAgain)) {
                        scanner.close();
                        return LocalDate.parse(tryAgain);
                    }
                }
            }
            return LocalDate.parse(date);
        }
    }

    public static LocalDate noYesterdays(LocalDate CheckIn) {
        if (!CheckIn.isBefore(LocalDate.now())) ;
        {
            while (CheckIn.isBefore(LocalDate.now())) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("We cannot backdate reservations." + "\n" +
                        "Please provide a check in date today, " +
                        LocalDate.now() + ", or later: ");
                LocalDate tryAgain = getValiDate(scanner.nextLine());
                if (!CheckIn.isBefore(LocalDate.now())) {
                    scanner.close();
                    return tryAgain;
                }
            }
            return CheckIn;
        }
    }

    public static LocalDate linearTimePLease(LocalDate CheckIn, LocalDate CheckOut) {
        if (CheckOut.isAfter(CheckIn)) ;
        {
            while (CheckOut.isBefore(CheckIn)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Visits must be at least one night long." + "\n" +
                        "Please provide a check out date at least one day after check in: ");
                LocalDate tryAgain = getValiDate(scanner.nextLine());
                if (CheckOut.isAfter(CheckIn)) {
                    scanner.close();
                    return tryAgain;
                }

            }
            return CheckOut;
        }
    }


}




