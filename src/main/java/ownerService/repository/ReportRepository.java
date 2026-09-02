package ownerService.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ownerService.entity.ListingReport;

import java.util.List;

public interface ReportRepository
        extends JpaRepository<ListingReport, Long> {

    List<ListingReport> findByRoomId(Long roomId);

    List<ListingReport> findByStatus(String status);

}