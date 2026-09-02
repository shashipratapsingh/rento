package ownerService.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ownerService.dto.RoomRequest;
import ownerService.dto.RoomResponse;
import ownerService.entity.Owner;
import ownerService.entity.Room;
import ownerService.enums.AvailabilityStatus;
import ownerService.enums.RoomType;
import ownerService.repository.OwnerRepository;
import ownerService.repository.RoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final OwnerRepository ownerRepository;

    public RoomResponse createRoom(RoomRequest request) {

        Owner owner = ownerRepository.findById(
                request.getOwnerId()
        ).orElseThrow(() ->
                new RuntimeException("Owner not found"));

        Room room = Room.builder()
                .owner(owner)
                .title(request.getTitle())
                .address(request.getAddress())
                .city(request.getCity())
                .locality(request.getLocality())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .rent(request.getRent())
                .description(request.getDescription())
                .roomType(request.getRoomType())
                .availability(
                        request.getAvailability() != null
                                ? request.getAvailability()
                                : AvailabilityStatus.AVAILABLE
                )
                .build();

        room = roomRepository.save(room);

        return mapToResponse(room);
    }

    public RoomResponse getRoom(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        return mapToResponse(room);
    }

    public List<RoomResponse> getRoomsByOwner(
            Long ownerId) {

        return roomRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public RoomResponse updateRoom(
            Long id,
            RoomRequest request) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        room.setTitle(request.getTitle());
        room.setAddress(request.getAddress());
        room.setCity(request.getCity());
        room.setLocality(request.getLocality());
        room.setLatitude(request.getLatitude());
        room.setLongitude(request.getLongitude());
        room.setRent(request.getRent());
        room.setDescription(request.getDescription());
        room.setRoomType(request.getRoomType());

        if (request.getAvailability() != null) {
            room.setAvailability(
                    request.getAvailability()
            );
        }

        roomRepository.save(room);

        return mapToResponse(room);
    }

    public void deleteRoom(Long id) {

        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found");
        }

        roomRepository.deleteById(id);
    }

    public RoomResponse markAsRented(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        room.setAvailability(
                AvailabilityStatus.OCCUPIED
        );

        roomRepository.save(room);

        return mapToResponse(room);
    }

    public RoomResponse updateAvailability(
            Long id,
            AvailabilityStatus status) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        room.setAvailability(status);

        roomRepository.save(room);

        return mapToResponse(room);
    }

    public List<RoomResponse> searchByCity(
            String city) {

        return roomRepository
                .findByCityIgnoreCaseAndAvailability(
                        city,
                        AvailabilityStatus.AVAILABLE
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<RoomResponse> searchByRent(
            Double minRent,
            Double maxRent) {

        return roomRepository
                .findByRentBetween(minRent, maxRent)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<RoomResponse> searchByRoomType(
            RoomType roomType) {

        return roomRepository
                .findByRoomType(roomType)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RoomResponse mapToResponse(Room room) {

        List<String> photos =
                room.getPhotos()
                        .stream()
                        .map(photo -> photo.getFileUrl())
                        .toList();

        return RoomResponse.builder()
                .id(room.getId())
                .ownerId(room.getOwner().getId())
                .ownerName(room.getOwner().getName())
                .title(room.getTitle())
                .address(room.getAddress())
                .city(room.getCity())
                .locality(room.getLocality())
                .latitude(room.getLatitude())
                .longitude(room.getLongitude())
                .rent(room.getRent())
                .description(room.getDescription())
                .roomType(room.getRoomType())
                .availability(room.getAvailability())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .photoUrls(photos)
                .build();
    }
}