package warungmakanbahariapi.wmbahari.repository;

import org.springframework.stereotype.Repository;
import warungmakanbahariapi.wmbahari.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
