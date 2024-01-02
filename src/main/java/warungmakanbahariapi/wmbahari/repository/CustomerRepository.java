package warungmakanbahariapi.wmbahari.repository;

import org.springframework.stereotype.Repository;
import warungmakanbahariapi.wmbahari.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    Customer findByName(String name);

}
