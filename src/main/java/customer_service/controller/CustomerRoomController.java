package customer_service.controller;

import customer_service.dto.OwnerContactResponse;
import customer_service.dto.ReportRequest;
import customer_service.dto.RoomPhotoResponse;
import customer_service.dto.RoomResponse;
import customer_service.service.CustomerRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers/rooms")
@RequiredArgsConstructor
public class CustomerRoomController {

    private final CustomerRoomService customerRoomService;


    // =========================
    // ROOM DETAILS
    // =========================

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> getRoom(  @PathVariable Long roomId) {
        return ResponseEntity.ok( customerRoomService.getRoom(roomId)  );
    }


    // =========================
    // CITY SEARCH
    // =========================

    @GetMapping("/search/city")
    public ResponseEntity<List<RoomResponse>> searchByCity(  @RequestParam String city) {
        return ResponseEntity.ok(
                customerRoomService.searchByCity(city)
        );
    }


    // =========================
    // RENT FILTER
    // =========================

    @GetMapping("/search/rent")
    public ResponseEntity<List<RoomResponse>> searchByRent(
            @RequestParam Double minRent,
            @RequestParam Double maxRent) {
        return ResponseEntity.ok(
                customerRoomService.searchByRent(
                        minRent,
                        maxRent
                )
        );
    }


    // =========================
    // ROOM TYPE FILTER
    // =========================

    @GetMapping("/search/type")
    public ResponseEntity<List<RoomResponse>> searchByType(
            @RequestParam String roomType) {

        return ResponseEntity.ok(
                customerRoomService.searchByType(roomType)
        );
    }


    // =========================
    // ROOM PHOTOS
    // =========================

    @GetMapping("/{roomId}/photos")
    public ResponseEntity<List<RoomPhotoResponse>> getPhotos(
            @PathVariable Long roomId) {

        return ResponseEntity.ok(
                customerRoomService.getPhotos(roomId)
        );
    }


    // =========================
    // REPORT ROOM
    // =========================

    @PostMapping("/{roomId}/report")
    public ResponseEntity<String> reportRoom(
            @PathVariable Long roomId,
            @Valid @RequestBody ReportRequest request) {

        return ResponseEntity.ok(
                customerRoomService.reportRoom(
                        roomId,
                        request
                )
        );
    }


    // =========================
    // OWNER CONTACT
    // =========================

    @GetMapping("/{roomId}/owner-contact")
    public ResponseEntity<OwnerContactResponse> getOwnerContact(
            @PathVariable Long roomId) {

        return ResponseEntity.ok(
                customerRoomService.getOwnerContact(roomId)
        );
    }
}