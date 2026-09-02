package customer_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportRequest {

    @NotNull
    private String reason;

    private String description;
}