public class HotelApplication {
    public static void main(String[] args) {
        // feeder data
        service.CustomerService.addCustomer("John", "Doe", "john@doe.com");
        model.IRoom room = new model.Room("1", model.RoomType.SINGLE, 10.00);
        service.ReservationService.addRoom(room);
        service.ReservationService.reserveARoom(service.CustomerService.customers.get("john@doe.com"), room,
                java.time.LocalDate.of(2024, 01, 01),
                java.time.LocalDate.of(2024, 01, 02));
        // end feeder data

        MainMenu.startMainMenu();
    }

}