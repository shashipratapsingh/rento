package ownerService.entity;
import jakarta.persistence.*;
import lombok.*;
import ownerService.enums.ReportReason;

import java.time.LocalDateTime;

@Entity
@Table(name = "listing_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListingReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    private Long reportedBy;

    @Enumerated(EnumType.STRING)
    private ReportReason reason;

    @Column(length = 2000)
    private String description;

    @Builder.Default
    private String status = "OPEN";

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();

    }
}