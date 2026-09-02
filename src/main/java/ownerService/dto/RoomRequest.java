package ownerService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ownerService.enums.AvailabilityStatus;
import ownerService.enums.RoomType;

@Data
public class RoomRequest {

    @NotNull
    private Long ownerId;

    @NotBlank
    private String title;

    private String address;

    private String city;

    private String locality;

    private Double latitude;

    private Double longitude;

    @NotNull
    private Double rent;

    private String description;

    @NotNull
    private RoomType roomType;

    private AvailabilityStatus availability;
}