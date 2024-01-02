package warungmakanbahariapi.wmbahari.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import warungmakanbahariapi.wmbahari.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String>, JpaSpecificationExecutor<Order> {
}
