package customer_service.service;

import customer_service.dto.OwnerContactResponse;
import customer_service.dto.ReportRequest;
import customer_service.dto.RoomPhotoResponse;
import customer_service.dto.RoomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "OWNER-SERVICE")
public interface OwnerServiceClient {

    @GetMapping("/internal/rooms/{id}")
    RoomResponse getRoom(
            @PathVariable("id") Long id
    );

    @GetMapping("/internal/rooms/search/city")
    List<RoomResponse> searchByCity(
            @RequestParam("city") String city
    );

    @GetMapping("/internal/rooms/search/rent")
    List<RoomResponse> searchByRent(
            @RequestParam("minRent") Double minRent,
            @RequestParam("maxRent") Double maxRent
    );

    @GetMapping("/internal/rooms/search/type")
    List<RoomResponse> searchByType(
            @RequestParam("roomType") String roomType
    );

    @GetMapping("/internal/rooms/{roomId}/photos")
    List<RoomPhotoResponse> getPhotos(
            @PathVariable("roomId") Long roomId
    );

    @PostMapping("/internal/reports/rooms/{roomId}")
    String createReport(
            @PathVariable("roomId") Long roomId,
            @RequestBody ReportRequest request
    );

    @GetMapping("/internal/rooms/{roomId}/owner-contact")
    OwnerContactResponse getOwnerContact(
            @PathVariable("roomId") Long roomId
    );
}