package ownerService.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ownerService.dto.OwnerRequest;
import ownerService.dto.OwnerResponse;
import ownerService.service.OwnerService;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(
            @Valid @RequestBody OwnerRequest request) {

        return ResponseEntity.ok(
                ownerService.createOwner(request)
        );
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwner(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ownerService.getOwner(id)
        );
    }

    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRequest request) {

        return ResponseEntity.ok(
                ownerService.updateOwner(id, request)
        );
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(
            @PathVariable Long id) {

        ownerService.deleteOwner(id);

        return ResponseEntity.ok(
                "Owner deleted successfully"
        );
    }
}