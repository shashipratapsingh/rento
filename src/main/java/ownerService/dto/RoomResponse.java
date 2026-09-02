package ownerService.dto;
import lombok.Builder;
import lombok.Data;
import ownerService.enums.AvailabilityStatus;
import ownerService.enums.RoomType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RoomResponse {

    private Long id;

    private Long ownerId;

    private String ownerName;

    private String title;

    private String address;

    private String city;

    private String locality;

    private Double latitude;

    private Double longitude;

    private Double rent;

    private String description;

    private RoomType roomType;

    private AvailabilityStatus availability;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<String> photoUrls;
}