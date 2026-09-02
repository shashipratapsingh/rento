package ownerService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ownerService.entity.RoomPhoto;
import ownerService.service.PhotoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomPhotoController {

    private final PhotoService photoService;
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/{roomId}/photos")
    public ResponseEntity<RoomPhoto> uploadPhoto(
            @PathVariable Long roomId,
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return ResponseEntity.ok(
                photoService.uploadPhoto(
                        roomId,
                        file
                )
        );
    }
    @PreAuthorize("hasAnyRole('OWNER', 'CUSTOMER')")
    @GetMapping("/{roomId}/photos")
    public ResponseEntity<List<RoomPhoto>>
    getPhotos(
            @PathVariable Long roomId) {

        return ResponseEntity.ok(
                photoService.getPhotos(roomId)
        );
    }
    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/photos/{photoId}")
    public ResponseEntity<String> deletePhoto(
            @PathVariable Long photoId)
            throws IOException {

        photoService.deletePhoto(photoId);

        return ResponseEntity.ok(
                "Photo deleted successfully"
        );
    }
}