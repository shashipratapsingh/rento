package ownerService.entity;
import jakarta.persistence.*;
import lombok.*;
import ownerService.enums.VerificationStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_photos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    private String fileName;

    private String fileUrl;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private VerificationStatus verificationStatus =
            VerificationStatus.PENDING;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {

        createdAt = LocalDateTime.now();

    }
}