package model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    public final Customer customer;
    private final IRoom room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    /* don't need to validate the Date format because the java class does it for me */
    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return this.room;
    }

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }
    // I'll be using LocalDate instead of Date and Calendar because it's less annoying

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    /* I trust you IntellIJ function.  Please don't betray me here */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return getCustomer().equals(that.getCustomer()) &&
                getRoom().equals(that.getRoom()) &&
                getCheckInDate().equals(that.getCheckInDate()) &&
                getCheckOutDate().equals(that.getCheckOutDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getRoom(), getCheckInDate(), getCheckOutDate());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
