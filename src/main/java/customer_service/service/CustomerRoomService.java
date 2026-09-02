package customer_service.service;

import customer_service.dto.OwnerContactResponse;
import customer_service.dto.ReportRequest;
import customer_service.dto.RoomPhotoResponse;
import customer_service.dto.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerRoomService {

    private final OwnerServiceClient ownerServiceClient;


    public RoomResponse getRoom(Long roomId) {
        return ownerServiceClient.getRoom(roomId);
    }


    public List<RoomResponse> searchByCity(String city) {

        return ownerServiceClient.searchByCity(city);
    }


    public List<RoomResponse> searchByRent(
            Double minRent,
            Double maxRent) {

        return ownerServiceClient.searchByRent(
                minRent,
                maxRent
        );
    }


    public List<RoomResponse> searchByType(String roomType) {

        return ownerServiceClient.searchByType(roomType);
    }


    public List<RoomPhotoResponse> getPhotos(Long roomId) {

        return ownerServiceClient.getPhotos(roomId);
    }


    public String reportRoom(
            Long roomId,
            ReportRequest request) {

        return ownerServiceClient.createReport(
                roomId,
                request
        );
    }


    public OwnerContactResponse getOwnerContact(Long roomId) {

        return ownerServiceClient.getOwnerContact(roomId);
    }
}