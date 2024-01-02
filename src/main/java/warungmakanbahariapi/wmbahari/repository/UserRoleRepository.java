package warungmakanbahariapi.wmbahari.repository;

import org.springframework.stereotype.Repository;
import warungmakanbahariapi.wmbahari.entity.ERole;
import warungmakanbahariapi.wmbahari.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {

    Optional<UserRole> findByRole(ERole role);
}
