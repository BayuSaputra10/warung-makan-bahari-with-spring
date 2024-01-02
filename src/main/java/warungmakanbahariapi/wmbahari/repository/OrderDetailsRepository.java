package warungmakanbahariapi.wmbahari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warungmakanbahariapi.wmbahari.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,String > {
}
