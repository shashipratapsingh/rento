package ownerService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ownerService.entity.Room;
import ownerService.entity.RoomPhoto;
import ownerService.repository.RoomPhotoRepository;
import ownerService.repository.RoomRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final RoomRepository roomRepository;
    private final RoomPhotoRepository photoRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public RoomPhoto uploadPhoto(
            Long roomId,
            MultipartFile file) throws IOException {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String originalName = file.getOriginalFilename();

        String fileName =
                System.currentTimeMillis()
                        + "_"
                        + originalName;

        Path roomDirectory = Paths.get(
                uploadDir,
                String.valueOf(roomId)
        );

        Files.createDirectories(roomDirectory);

        Path filePath =
                roomDirectory.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        String fileUrl =
                "/uploads/rooms/"
                        + roomId
                        + "/"
                        + fileName;

        RoomPhoto photo = RoomPhoto.builder()
                .room(room)
                .fileName(fileName)
                .fileUrl(fileUrl)
                .build();

        return photoRepository.save(photo);
    }

    public List<RoomPhoto> getPhotos(Long roomId) {

        return photoRepository.findByRoomId(roomId);

    }

    public void deletePhoto(Long photoId)
            throws IOException {

        RoomPhoto photo =
                photoRepository.findById(photoId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Photo not found"
                                ));

        Path path = Paths.get(
                uploadDir,
                String.valueOf(photo.getRoom().getId()),
                photo.getFileName()
        );

        Files.deleteIfExists(path);

        photoRepository.delete(photo);
    }
}