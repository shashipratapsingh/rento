package ownerService.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ownerService.dto.OwnerRequest;
import ownerService.dto.OwnerResponse;
import ownerService.entity.Owner;
import ownerService.repository.OwnerRepository;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerResponse createOwner(OwnerRequest request) {

        if (ownerRepository.findByMobile(request.getMobile()).isPresent()) {
            throw new RuntimeException(
                    "Owner with this mobile already exists"
            );
        }

        Owner owner = Owner.builder()
                .name(request.getName())
                .mobile(request.getMobile())
                .address(request.getAddress())
                .build();

        owner = ownerRepository.save(owner);

        return mapToResponse(owner);
    }

    public OwnerResponse getOwner(Long id) {

        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        return mapToResponse(owner);
    }

    public OwnerResponse updateOwner(
            Long id,
            OwnerRequest request) {

        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        owner.setName(request.getName());
        owner.setMobile(request.getMobile());
        owner.setAddress(request.getAddress());

        ownerRepository.save(owner);

        return mapToResponse(owner);
    }

    public void deleteOwner(Long id) {

        if (!ownerRepository.existsById(id)) {
            throw new RuntimeException("Owner not found");
        }

        ownerRepository.deleteById(id);
    }

    private OwnerResponse mapToResponse(Owner owner) {

        return OwnerResponse.builder()
                .id(owner.getId())
                .name(owner.getName())
                .mobile(owner.getMobile())
                .address(owner.getAddress())
                .mobileVerified(owner.isMobileVerified())
                .verificationStatus(
                        owner.getVerificationStatus()
                )
                .build();
    }
}