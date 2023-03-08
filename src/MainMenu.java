import model.Customer;
import service.CustomerService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainMenu {
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
                    System.out.print("see my reservation");
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

    public static LocalDate valiDate(String date) {
        return null;
    }
    private static void findAndReserveARoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID (i.e. email) for the customer making a reservation:");
        String customerEmail = scanner.nextLine();
        if (Customer.isValidEmail(customerEmail)) {

        } else {
            throw new IllegalArgumentException();
        }
        Customer customer = CustomerService.getCustomer(customerEmail);

        if (customer != null) {
            System.out.println("What date would " + customer.getFirstName() + " like to check in?" + "\n" +
                    "Enter date in yyyy-mm-dd format:");
            // I'd like to say I am using yyy-mm-dd here because it reduces the chance of an error
            // via the difference in USA vs EU date formats, but it's really because LocalDate parses
            // it without me having to do extra messing around1
            try {
                LocalDate CheckIn = LocalDate.parse(scanner.nextLine());
                System.out.println(CheckIn);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date in the format yyyy-mm-dd");
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
                        System.out.println("create an account");
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
}
