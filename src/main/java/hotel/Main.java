package hotel;

import hotel.repository.HotelRepositoryImpl;
import hotel.service.HotelService;
import hotel.ui.HotelConsole;

public class Main {
    public static void main(String[] args) {
        HotelRepositoryImpl hotelRepository = new HotelRepositoryImpl();
        HotelService hotelService = new HotelService(hotelRepository);
        HotelConsole hotelConsole = new HotelConsole(hotelService);

        hotelConsole.runConsole();
    }
}