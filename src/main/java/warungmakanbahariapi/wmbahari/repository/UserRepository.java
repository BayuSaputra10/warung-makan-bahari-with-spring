package warungmakanbahariapi.wmbahari.repository;

import warungmakanbahariapi.wmbahari.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
