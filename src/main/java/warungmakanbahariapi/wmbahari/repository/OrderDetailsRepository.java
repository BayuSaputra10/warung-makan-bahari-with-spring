package warungmakanbahariapi.wmbahari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warungmakanbahariapi.wmbahari.entity.OrderDetails;
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,String > {
}
