package hotel.domain;

public class RoomValidator {
    public static void validateRoom(HotelRoom room) {
        if (room.getName() == null) {
            throw new RuntimeException("Room name can't be null!");
        }
        if (room.getPrice() <= 0) {
            throw new RuntimeException("Price should be positive!");
        }
        if (room.getBedsNo() < 1 || room.getBedsNo() > 5) {
            throw new RuntimeException("Beds number should be betwen 1 and 5!");
        }
        if (!room.getType().equals("Regular") && !room.getType().equals("Business") && !room.getType().equals("Executive") && !room.getType().equals("VIP")) {
            throw new RuntimeException("Room type should be one of these Regular, Business, Executive sau VIP!");
        }
    }
}
