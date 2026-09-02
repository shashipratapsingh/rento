package ownerService.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ownerService.dto.ReportRequest;
import ownerService.entity.ListingReport;
import ownerService.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/rooms/{roomId}")
    public ResponseEntity<ListingReport> createReport(
            @PathVariable Long roomId,
            @Valid @RequestBody ReportRequest request) {

        return ResponseEntity.ok(
                reportService.createReport(
                        roomId,
                        request
                )
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ListingReport>>
    getAllReports() {

        return ResponseEntity.ok(
                reportService.getAllReports()
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ListingReport>
    getReport(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reportService.getReport(id)
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/resolve")
    public ResponseEntity<ListingReport>
    resolveReport(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reportService.resolveReport(id)
        );
    }
}