package ownerService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ownerService.entity.Room;
import ownerService.enums.AvailabilityStatus;
import ownerService.enums.RoomType;

import java.util.List;

public interface RoomRepository
        extends JpaRepository<Room, Long> {

    List<Room> findByOwnerId(Long ownerId);

    List<Room> findByAvailability(
            AvailabilityStatus availability
    );

    List<Room> findByCityIgnoreCase(String city);

    List<Room> findByCityIgnoreCaseAndAvailability(
            String city,
            AvailabilityStatus availability
    );

    List<Room> findByRentBetween(
            Double minRent,
            Double maxRent
    );

    List<Room> findByRoomType(RoomType roomType);

}