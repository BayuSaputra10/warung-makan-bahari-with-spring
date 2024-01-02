package warungmakanbahariapi.wmbahari.repository;

import org.springframework.stereotype.Repository;
import warungmakanbahariapi.wmbahari.entity.TransType;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TransTypeRepository extends JpaRepository<TransType,String> {
}
