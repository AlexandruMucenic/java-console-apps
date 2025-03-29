package hotel.repository;

import java.util.ArrayList;
import java.util.List;

import hotel.domain.HotelRoom;

public class HotelRepositoryImpl implements HotelRepository {
    private List<HotelRoom> roomList;

    public HotelRepositoryImpl() {
        this.roomList = new ArrayList<>();
    }

    public List<HotelRoom> findAll() {
        return roomList;
    }

    public void save(HotelRoom newHotelRoom) {
        for (HotelRoom room : roomList) {
            if (newHotelRoom.getId() == room.getId()) {
                throw new RuntimeException("Room already exists!");
            }
        }
        roomList.add(newHotelRoom);
    }

    public void update(HotelRoom newRoom) {
        for (HotelRoom room : roomList) {
            if (room.getId() == newRoom.getId()) {
                room.setName(newRoom.getName());
                room.setPrice(newRoom.getPrice());
                room.setBedsNo(newRoom.getBedsNo());
                room.setType(newRoom.getType());
            }
        }
    }

    public void deleteById(int id) {
        roomList.removeIf(room -> room.getId() == id);
    }
}










