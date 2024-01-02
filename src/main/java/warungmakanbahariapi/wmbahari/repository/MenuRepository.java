package warungmakanbahariapi.wmbahari.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import warungmakanbahariapi.wmbahari.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface MenuRepository extends JpaRepository<Menu,String>, JpaSpecificationExecutor<Menu> {
}
