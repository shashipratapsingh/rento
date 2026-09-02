package ownerService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ownerService.entity.RoomPhoto;

import java.util.List;

public interface RoomPhotoRepository
        extends JpaRepository<RoomPhoto, Long> {

    List<RoomPhoto> findByRoomId(Long roomId);

}