package ownerService.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ownerService.enums.ReportReason;

@Data
public class ReportRequest {

    private Long reportedBy;

    @NotNull
    private ReportReason reason;

    private String description;
}