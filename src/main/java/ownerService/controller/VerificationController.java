package ownerService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ownerService.entity.Owner;
import ownerService.entity.RoomPhoto;
import ownerService.service.VerificationService;

@RestController
@RequestMapping("/api/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/owners/{ownerId}/verify")
    public ResponseEntity<Owner> verifyOwner(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(
                verificationService.verifyOwner(
                        ownerId
                )
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/owners/{ownerId}/reject")
    public ResponseEntity<Owner> rejectOwner(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(
                verificationService.rejectOwner(
                        ownerId
                )
        );
    }

    @PatchMapping("/photos/{photoId}/verify")
    public ResponseEntity<RoomPhoto> verifyPhoto(
            @PathVariable Long photoId) {

        return ResponseEntity.ok(
                verificationService.verifyPhoto(
                        photoId
                )
        );
    }

    @PatchMapping("/photos/{photoId}/reject")
    public ResponseEntity<RoomPhoto> rejectPhoto(
            @PathVariable Long photoId) {

        return ResponseEntity.ok(
                verificationService.rejectPhoto(
                        photoId
                )
        );
    }
}