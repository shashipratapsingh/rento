package customer_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomPhotoResponse {

    private Long id;

    private String fileName;

    private String fileUrl;

    private String verificationStatus;

    private LocalDateTime createdAt;
}