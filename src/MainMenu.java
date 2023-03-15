import model.Customer;
import model.IRoom;
import service.CustomerService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

import static service.ReservationService.*;


public class MainMenu {
    // allowing a more customer friendly date format for output that might be exposed to the guest
    static DateTimeFormatter dmy = DateTimeFormatter.ofPattern("EEE d MMM YYYY");

    public static void printMainMenu() {
        System.out.println("" +
                "  CUSTOMER RESERVATION MENU  " + "\n" +
                "═════════════════════════════" + "\n" +
                "[1] Find and reserve a room  " + "\n" +
                "[2] See customer reservation " + "\n" +
                "[3] Create an account        " + "\n" +
                "[4] Admin                    " + "\n" +
                "[5] Exit                     " + "\n" +
                "═════════════════════════════" + "\n" +
                "Choose an option by number:");
        // I changed this to brackets because I think they look better;
        // I changed choice 2 to "see customer" because given the other
        // options and other menu, it makes no sense that a customer
        // would be using this directly.  This is clearly an agent
        // screen.
    }

    public static void startMainMenu() {
        String menuOptions = "";
        Scanner scanner = new Scanner(System.in);

        printMainMenu();

        try {
            menuOptions = scanner.nextLine();
            switch (menuOptions) {
                case "1":
                    findAndReserveARoom();
                    break;
                case "2":
                    lookUpReservation();
                    break;
                case "3":
                    System.out.println("create an account");
                    break;
                case "4":
                    System.out.println("go to admin menu");
                    break;
                case "5":
                    System.out.println("Leave program");
                    break;
                default:
                    System.out.println("Please enter one of the given options:");
                    startMainMenu();
            }
        } catch (Exception e) {
            System.out.println("well that was no good");
        }
    }


    private static void findAndReserveARoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID (i.e. email) for the customer making a reservation:");
        String customerEmail = CustomerService.getValidEmail(scanner.nextLine());
        Customer customer = CustomerService.getCustomer(customerEmail);

        if (customer != null) {

            System.out.println("What date would " + customer.getFirstName() + " like to check in?" + "\n" +
                    "Enter date in yyyy-mm-dd format:");
            // I'd like to say I am using yyyy-mm-dd here because it reduces the chance of an error
            // via the dd/mm/yyyy and mm/dd/yyyy date formats, but it's really because it parses
            // without needing much extra messing around
            LocalDate checkIn = noYesterdays(getValiDate(scanner.nextLine()));
            System.out.println("What date would " + customer.getFirstName() + " like to check out?" + "\n" +
                    "Enter date in yyyy-mm-dd format:");
            LocalDate checkOut = linearTimePlease(checkIn, getValiDate(scanner.nextLine()));


            Map<String, IRoom> availableRooms = findRooms(checkIn, checkOut);
            if (!availableRooms.isEmpty()) {
                System.out.println("These rooms are available for a stay from " +
                        dmy.format(checkIn) + " to " + dmy.format(checkOut));
                printRooms(availableRooms);
            } else {
                System.out.println("We are sorry.  There are no available rooms for a stay from " +
                        dmy.format(checkIn) + " to " + dmy.format(checkOut));
                System.out.println("\nWe'll check the following week for alternatives.");
                Map<String, IRoom> altRooms = findRooms(checkIn.plusWeeks(1), checkOut.plusWeeks(1));
                if (!altRooms.isEmpty()) {
                    System.out.println("\nThese rooms are available for a stay from " +
                            dmy.format(checkIn.plusWeeks(1)) + " to " + dmy.format(checkOut.plusWeeks(1)));
                    printRooms(altRooms);
                } else {
                    System.out.println("no room a the inn");
                }
            }

        } else {
            System.out.println("There is no customer with the ID: " + customerEmail + ".");
            System.out.println("Do you want to: " + "\n" +
                    "[1] Try a different email" + "\n" +
                    "[2] Create a new customer account using this email" + "\n" +
                    "[3] Return to the Main Menu" + "\n" +
                    "═════════════════════════════" + "\n" +
                    "Choose an option by number:");
            try {
                String menuOptions = "";
                menuOptions = scanner.nextLine();
                switch (menuOptions) {
                    case "1":
                        findAndReserveARoom();
                        break;
                    case "2":
                        System.out.println("create an account 💜💙💚💛🧡");

                        break;
                    case "3":
                        startMainMenu();
                        break;
                    default:
                        System.out.println("Please enter one of the given options:");
                        startMainMenu();
                }
            } catch (Exception e) {
                System.out.println("well that was no good");
            }
        }
    }

    public static void lookUpReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID (i.e. email) for the customer:");
        String customerEmail = CustomerService.getValidEmail(scanner.nextLine());
        Customer customer = CustomerService.getCustomer(customerEmail);

        if (customer != null) {
            printReservationList(getCustomersReservation(customer));

        } else {
            System.out.println("There is no customer with the ID: " + customerEmail + ".");
            System.out.println("Do you want to: " + "\n" +
                    "[1] Try a different email" + "\n" +
//          this menu does not get a create customer option because nothing is
//          being created here only searched so it's no more effort to just make a
//          new customer from the main menu
                    "[2] Return to the Main Menu" + "\n" +
                    "═════════════════════════════" + "\n" +
                    "Choose an option by number:");
            try {
                String menuOptions = "";
                menuOptions = scanner.nextLine();
                switch (menuOptions) {
                    case "1":
                        findAndReserveARoom();
                        break;
                    case "2":
                        startMainMenu();
                        break;
                    default:
                        System.out.println("Please enter one of the given options:");
                        startMainMenu();
                }
            } catch (Exception e) {
                System.out.println("well that was no good");
            }
        }
    }
}
