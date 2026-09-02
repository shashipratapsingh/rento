package ownerService.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ownerService.entity.Owner;

import java.util.Optional;

public interface OwnerRepository
        extends JpaRepository<Owner, Long> {

    Optional<Owner> findByMobile(String mobile);

}