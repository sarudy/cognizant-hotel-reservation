import api.AdminResource;
import model.Customer;
import service.CustomerService;

import java.util.*;

import static api.AdminResource.*;

public class AdminMenu {
    public static void printAdminMenu() {
        System.out.println("" +
                "      ADMINISTRATOR MENU       " + "\n" +
                "═══════════════════════════════" + "\n" +
                "[1] See all Customers          " + "\n" +
                "[2] See all rooms              " + "\n" +
                "[3] See all Reservations       " + "\n" +
                "[4] See a room                 " + "\n" +
                "[5] Back to Main Menu          " + "\n" +
                "═══════════════════════════════" + "\n" +
                "Choose an option by number:");
    }

    public static void startAdminMenu() {
        String menuOptions = "";
        Scanner scanner = new Scanner(System.in);

        printAdminMenu();

        try {
            menuOptions = scanner.nextLine();
            switch (menuOptions) {
                case "1":
                    System.out.println(getAllCustomers());
                    startAdminMenu();
                    break;
                case "2":
                    System.out.println(getAllRooms());
                    break;
                case "3":
                    displayAllReservations();
                    break;
                case "4":
                    System.out.println("Add a Room");
                    break;
                case "5":
                    MainMenu.startMainMenu();
                    break;
                default:
                    System.out.println("Please enter one of the given options:");
                    MainMenu.startMainMenu();
            }
        } catch (Exception e) {
            System.out.println("well that was no good");
        }
    }

}
