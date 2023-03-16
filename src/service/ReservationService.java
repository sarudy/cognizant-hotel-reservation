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

    private static boolean isBooked(final String roomNumber, final LocalDate checkInDate, final LocalDate checkOutDate) {
        for (Reservation reservation : reservations) {
            if (roomNumber == reservation.getRoom().getRoomNumber() &&
                    checkInDate.isBefore(reservation.getCheckOutDate()) &&
                    checkOutDate.isAfter(reservation.getCheckInDate())) {
                return true;
            }
        }
        return false;
    }


    public static Map<String, IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        Map<String, IRoom> booked = new HashMap<>();
        Map<String, IRoom> open = new HashMap<>();
//        System.out.println("in " + checkInDate + ", out " + checkOutDate);
        for (Map.Entry<String, IRoom> room : rooms.entrySet()) {
            if (isBooked(room.getKey(), checkInDate, checkOutDate)) {
                booked.put(room.getKey(), room.getValue());
//                System.out.println(room.getKey() + " is booked");
            } else {
//               System.out.println(room.getValue());
                open.put(room.getKey(), room.getValue());
//                System.out.println(room.getKey() + " is available");
            }
        }
        return open;
    }

    public static void printRooms(Map<String, IRoom> theseRooms) {
        Map<String, IRoom> sortedRooms = new TreeMap<>(theseRooms);
//      I realize how silly it is to throw a TreeMap in here, a thing I only just learned existed because Maps
//      don't really sort and it offended my aesthetic sense to have rooms listed all higglty pigglety in any order
//      and this does get the job done.  I have a suspicion I could go back and make rooms into a TreeMap from the
//      get go but I am already on thin ice for using LocalDate.  I am also not 100 percent on what TreeMaps are
//      about in a more global sense and I don't think I need to go off on another tangent right this moment.
        for (Map.Entry<String, IRoom> thisRoom : sortedRooms.entrySet()) {
            System.out.println(thisRoom.getValue().getRoomType() + " ROOM: " + thisRoom.getValue().getRoomNumber() +
                    "\t" + "PRICE: " + formatter.format(thisRoom.getValue().getRoomPrice()) + " per night");
        }
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

    public static void printReservationList(Collection<Reservation> reservedBy) {
        // I really want to sort the collection by checkin first but it's not working out
        for (Reservation reservation : reservedBy) {
            printAReservation(reservation);
        }

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
            } else {
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
        }
        return LocalDate.parse(date);
    }


    public static LocalDate noYesterdays(LocalDate CheckIn) {
        if (!CheckIn.isBefore(LocalDate.now())) ;
        {
            while (CheckIn.isBefore(LocalDate.now())) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("We cannot backdate reservations." + "\n" +
                        "Please provide a check in date today (" +
                        LocalDate.now() + ") or later: ");
                LocalDate tryAgain = getValiDate(scanner.nextLine());
                if (!CheckIn.isAfter(LocalDate.now())) {
                    scanner.close();
                    return tryAgain;
                }
            }
            return CheckIn;
        }
    }

    public static LocalDate linearTimePlease(LocalDate CheckIn, LocalDate CheckOut) {
        if (CheckOut.isAfter(CheckIn)) ;
        {
            while (CheckOut.isBefore(CheckIn)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Visits must be at least one night long." + "\n" +
                        "Please provide a check out date at least one day after check in: ");
                LocalDate tryAgain = getValiDate(scanner.nextLine());
                if (CheckOut.isAfter(CheckIn)) {
                    return tryAgain;
                }
            }
            return CheckOut;
        }
    }
}




