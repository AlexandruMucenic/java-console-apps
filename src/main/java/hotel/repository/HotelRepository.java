package hotel.repository;

import java.util.List;

import hotel.domain.HotelRoom;

public interface HotelRepository {
    List<HotelRoom> findAll();

    void save(HotelRoom room);

    void update(HotelRoom newRoom);

    void deleteById(int id);
}
