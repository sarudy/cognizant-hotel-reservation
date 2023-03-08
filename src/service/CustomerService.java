package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
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
