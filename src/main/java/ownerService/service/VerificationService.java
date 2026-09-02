package ownerService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ownerService.entity.Owner;
import ownerService.entity.RoomPhoto;
import ownerService.enums.VerificationStatus;
import ownerService.repository.OwnerRepository;
import ownerService.repository.RoomPhotoRepository;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final OwnerRepository ownerRepository;
    private final RoomPhotoRepository photoRepository;

    public Owner verifyOwner(Long ownerId) {

        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        owner.setVerificationStatus(
                VerificationStatus.VERIFIED
        );

        return ownerRepository.save(owner);
    }

    public Owner rejectOwner(Long ownerId) {

        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        owner.setVerificationStatus(
                VerificationStatus.REJECTED
        );

        return ownerRepository.save(owner);
    }

    public RoomPhoto verifyPhoto(Long photoId) {

        RoomPhoto photo =
                photoRepository.findById(photoId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Photo not found"
                                ));

        photo.setVerificationStatus(
                VerificationStatus.VERIFIED
        );

        return photoRepository.save(photo);
    }

    public RoomPhoto rejectPhoto(Long photoId) {

        RoomPhoto photo =
                photoRepository.findById(photoId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Photo not found"
                                ));

        photo.setVerificationStatus(
                VerificationStatus.REJECTED
        );

        return photoRepository.save(photo);
    }
}