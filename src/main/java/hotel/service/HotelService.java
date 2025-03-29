package hotel.service;

import java.util.Comparator;
import java.util.List;

import hotel.domain.HotelRoom;
import hotel.domain.RoomValidator;
import hotel.repository.HotelRepositoryImpl;

public class HotelService {
    private HotelRepositoryImpl hotelRepository;

    public HotelService(HotelRepositoryImpl hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    public void addRoom(HotelRoom newRoom) {
        RoomValidator.validateRoom(newRoom);
        hotelRepository.save(newRoom);
    }

    public List<HotelRoom> getAllRooms() {
        return hotelRepository.findAll();
    }

    public void updateRoom(HotelRoom newRoom) {
        RoomValidator.validateRoom(newRoom);
        hotelRepository.update(newRoom);

        List<HotelRoom> allRooms = hotelRepository.findAll();
        System.out.println(allRooms);
    }

    public void deleteRoom(int id) {
        hotelRepository.deleteById(id);
    }

    public List<HotelRoom> filterRoomsByProfitability() {
        List<HotelRoom> rooms = hotelRepository.findAll();

        rooms.sort(new Comparator<HotelRoom>() {
            public int compare(HotelRoom room1, HotelRoom room2) {
                double pricePerBed1 = room1.getPrice() / room1.getBedsNo();
                double pricePerBed2 = room2.getPrice() / room2.getBedsNo();
                return Double.compare(pricePerBed1, pricePerBed2);
            }
        });
        return rooms;
    }

    public float getAverageBedsNo(String roomType) {
        int totalBeds = 0;
        int roomCount = 0;

        for (HotelRoom room : hotelRepository.findAll()) {
            if (room.getType().equalsIgnoreCase(roomType)) {
                totalBeds += room.getBedsNo();
                roomCount++;
            }
        }

        if (roomCount == 0) {
            throw new RuntimeException("No room found for this type!");
        }

        return (float) totalBeds / roomCount;
    }
}
