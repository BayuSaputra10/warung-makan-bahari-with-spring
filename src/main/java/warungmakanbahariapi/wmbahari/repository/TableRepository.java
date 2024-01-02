package warungmakanbahariapi.wmbahari.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import warungmakanbahariapi.wmbahari.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TableRepository extends JpaRepository<Table,String>, JpaSpecificationExecutor<Table> {
}
