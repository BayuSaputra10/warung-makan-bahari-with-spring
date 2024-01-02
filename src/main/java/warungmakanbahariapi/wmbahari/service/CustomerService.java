package warungmakanbahariapi.wmbahari.service;


import org.springframework.data.domain.Page;
import warungmakanbahariapi.wmbahari.entity.Customer;
import warungmakanbahariapi.wmbahari.model.request.CustomerRequest;
import warungmakanbahariapi.wmbahari.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest customer);

    CustomerResponse deleteById(String id);

    CustomerResponse findById(String customer);

    List<Customer> getAll();

    CustomerResponse updateCustomer(CustomerRequest customer);
}
