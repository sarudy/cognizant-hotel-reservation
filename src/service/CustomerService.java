package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerService {
    // I used a HashMap instead of an ArrayList because the lesson said it was more efficient.  I have been
    // cursing that choice for the entire project.
    public static final Map<String, Customer> customers = new HashMap<String, Customer>();
    private static CustomerService INSTANCE;

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }

    public static String getValidEmail(String email) {
        if (!Customer.isValidEmail(email)) {
            boolean goodEmail = false;
            while (!goodEmail) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Invalid email format.  Please enter the email again:");
                String tryAgain = scanner.nextLine();
                goodEmail = Customer.isValidEmail(tryAgain);
                if (Customer.isValidEmail(tryAgain)) {
                    scanner.close();
                    return tryAgain.toLowerCase();
                }
            }
        }
        return email.toLowerCase();
        // I was thinking as long as I was in here I should make all the emails lowercase to save lookup issues later.
    }

    public static void addCustomer(String firstName, String lastName, String email) {
        try {
            Customer customer = new Customer(firstName, lastName, email);
            customers.put(email, customer);
        } catch (IllegalArgumentException e) {
            System.out.println("Please use a valid email address.");
        }
    }

    public static Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers() {
        return customers.values();
    }

}
