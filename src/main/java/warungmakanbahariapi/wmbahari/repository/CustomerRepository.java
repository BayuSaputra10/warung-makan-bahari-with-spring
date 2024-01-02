package warungmakanbahariapi.wmbahari.repository;

import warungmakanbahariapi.wmbahari.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    Customer findByName(String name);

}
