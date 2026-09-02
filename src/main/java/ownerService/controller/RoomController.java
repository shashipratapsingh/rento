package ownerService.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ownerService.dto.RoomRequest;
import ownerService.dto.RoomResponse;
import ownerService.enums.AvailabilityStatus;
import ownerService.enums.RoomType;
import ownerService.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(
            @Valid @RequestBody RoomRequest request) {

        return ResponseEntity.ok(
                roomService.createRoom(request)
        );
    }
    @PreAuthorize("hasAnyRole('OWNER', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoom(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                roomService.getRoom(id)
        );
    }
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<RoomResponse>>
    getOwnerRooms(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(
                roomService.getRoomsByOwner(ownerId)
        );
    }
    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomRequest request) {

        return ResponseEntity.ok(
                roomService.updateRoom(id, request)
        );
    }
    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(
            @PathVariable Long id) {

        roomService.deleteRoom(id);

        return ResponseEntity.ok(
                "Room deleted successfully"
        );
    }
    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{id}/mark-rented")
    public ResponseEntity<RoomResponse> markAsRented(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                roomService.markAsRented(id)
        );
    }
    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{id}/availability")
    public ResponseEntity<RoomResponse>
    updateAvailability(
            @PathVariable Long id,
            @RequestParam AvailabilityStatus status) {

        return ResponseEntity.ok(
                roomService.updateAvailability(
                        id,
                        status
                )
        );
    }
    @PreAuthorize("hasAnyRole('OWNER', 'CUSTOMER')")
    @GetMapping("/search/city")
    public ResponseEntity<List<RoomResponse>>
    searchByCity(
            @RequestParam String city) {

        return ResponseEntity.ok(
                roomService.searchByCity(city)
        );
    }

    @GetMapping("/search/rent")
    public ResponseEntity<List<RoomResponse>>
    searchByRent(
            @RequestParam Double minRent,
            @RequestParam Double maxRent) {

        return ResponseEntity.ok(
                roomService.searchByRent(
                        minRent,
                        maxRent
                )
        );
    }

    @GetMapping("/search/type")
    public ResponseEntity<List<RoomResponse>>
    searchByType(
            @RequestParam RoomType roomType) {

        return ResponseEntity.ok(
                roomService.searchByRoomType(roomType)
        );
    }
}