package warungmakanbahariapi.wmbahari.repository;

import org.springframework.stereotype.Repository;
import warungmakanbahariapi.wmbahari.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {
}
