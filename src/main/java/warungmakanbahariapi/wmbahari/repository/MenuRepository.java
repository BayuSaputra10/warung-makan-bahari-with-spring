package warungmakanbahariapi.wmbahari.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import warungmakanbahariapi.wmbahari.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,String>, JpaSpecificationExecutor<Menu> {
}
