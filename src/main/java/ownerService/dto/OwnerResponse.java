package ownerService.dto;
import lombok.Builder;
import lombok.Data;
import ownerService.enums.VerificationStatus;

@Data
@Builder
public class OwnerResponse {

    private Long id;

    private String name;

    private String mobile;

    private String address;

    private boolean mobileVerified;

    private VerificationStatus verificationStatus;
}