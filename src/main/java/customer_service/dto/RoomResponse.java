package customer_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoomResponse {

    private Long id;

    private Long ownerId;

    private String ownerName;

    private Boolean ownerVerified;

    private String ownerResponseRate;

    private String title;

    private String address;

    private String city;

    private String locality;

    private Double latitude;

    private Double longitude;

    private Double rent;

    private String description;

    private String roomType;

    private String availability;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<RoomPhotoResponse> photos;
}