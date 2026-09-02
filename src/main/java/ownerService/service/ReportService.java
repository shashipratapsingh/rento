package ownerService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ownerService.dto.ReportRequest;
import ownerService.entity.ListingReport;
import ownerService.entity.Room;
import ownerService.repository.ReportRepository;
import ownerService.repository.RoomRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final RoomRepository roomRepository;

    public ListingReport createReport(
            Long roomId,
            ReportRequest request) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        ListingReport report =
                ListingReport.builder()
                        .room(room)
                        .reportedBy(
                                request.getReportedBy()
                        )
                        .reason(request.getReason())
                        .description(
                                request.getDescription()
                        )
                        .status("OPEN")
                        .build();

        return reportRepository.save(report);
    }

    public List<ListingReport> getAllReports() {

        return reportRepository.findAll();

    }

    public ListingReport getReport(Long id) {

        return reportRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Report not found"
                        ));
    }

    public ListingReport resolveReport(Long id) {

        ListingReport report =
                reportRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Report not found"
                                ));

        report.setStatus("RESOLVED");
        report.setResolvedAt(
                LocalDateTime.now()
        );

        return reportRepository.save(report);
    }
}