package hotel.ui;

import java.util.List;
import java.util.Scanner;

import hotel.domain.HotelRoom;
import hotel.service.HotelService;

public class HotelConsole {
    private Scanner scanner = new Scanner(System.in);
    private HotelService hotelService;

    public HotelConsole(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void printMenu() {
        System.out.println("0. Close app");
        System.out.println("1. Add room");
        System.out.println("2. Show rooms");
        System.out.println("3. Update room");
        System.out.println("4. Delete room");
        System.out.println("5. Profitability report");
        System.out.println("6. Type report");
    }

    public int getMenuOption() {
        System.out.println("Choose an option:");
        return scanner.nextInt();
    }

    public HotelRoom getRoomDetails() {
        System.out.println("Enter an id: ");
        int id = scanner.nextInt();
        System.out.println("Enter the name of the room: ");
        String name = scanner.next();
        System.out.println("Enter the price: ");
        float price = scanner.nextFloat();
        System.out.println("Enter the number of beds: ");
        int bedsNo = scanner.nextInt();
        System.out.println("Enter room type: ");
        String type = scanner.next();

        return new HotelRoom(id, name, price, bedsNo, type);
    }

    private void addRoom(HotelRoom room) {
        try {
            hotelService.addRoom(room);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
    }

    private void showRooms() {
        List<HotelRoom> rooms = hotelService.getAllRooms();
        for (HotelRoom room : rooms) {
            System.out.println(room.toString());
        }
    }

    private void updateRoom(HotelRoom newRoom) {
        try {
            hotelService.updateRoom(newRoom);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
    }

    private void deleteRoom(int id) {
        hotelService.deleteRoom(id);
    }

    public void filterRoomsByProfitability() {
        System.out.println("Rooms order by profitability:");
        for (HotelRoom room : hotelService.filterRoomsByProfitability()) {
            System.out.println(room.toString());
        }
    }

    public void getAverageBedsNo(String roomType) {
        try {
            int avgBedsNo = Math.round(hotelService.getAverageBedsNo(roomType));
            System.out.println("Average number of beds for this room type is: " + avgBedsNo);
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
        }
    }

    public void runConsole() {
        addRoom(new HotelRoom(1, "room1", 33, 3, "VIP"));
        addRoom(new HotelRoom(2, "room2", 60, 1, "VIP"));
        addRoom(new HotelRoom(3, "room2", 50, 4, "VIP"));
        addRoom(new HotelRoom(4, "room2", 30, 2, "VIP"));
        addRoom(new HotelRoom(5, "room2", 20, 4, "Executive"));

        boolean flag = true;
        while (flag) {
            printMenu();
            int option = getMenuOption();
            switch (option) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    addRoom(getRoomDetails());
                    break;
                case 2:
                    showRooms();
                    break;
                case 3:
                    updateRoom(getRoomDetails());
                    break;
                case 4:
                    System.out.println("Enter room id: ");
                    deleteRoom(scanner.nextInt());
                    break;
                case 5:
                    filterRoomsByProfitability();
                    break;
                case 6:
                    System.out.println("Enter room type: ");
                    getAverageBedsNo(scanner.next());
                    break;
            }
        }
    }
}
