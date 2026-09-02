package customer_service.dto;


import lombok.Data;

@Data
public class OwnerContactResponse {

    private Long ownerId;

    private String ownerName;

    private String mobile;

    private Boolean verified;

    private String responseRate;
}