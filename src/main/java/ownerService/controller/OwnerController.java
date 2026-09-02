package ownerService.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ownerService.dto.OwnerRequest;
import ownerService.dto.OwnerResponse;
import ownerService.service.OwnerService;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(
            @Valid @RequestBody OwnerRequest request) {

        return ResponseEntity.ok(
                ownerService.createOwner(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwner(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ownerService.getOwner(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRequest request) {

        return ResponseEntity.ok(
                ownerService.updateOwner(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(
            @PathVariable Long id) {

        ownerService.deleteOwner(id);

        return ResponseEntity.ok(
                "Owner deleted successfully"
        );
    }
}