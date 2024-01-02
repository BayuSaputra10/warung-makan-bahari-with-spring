package warungmakanbahariapi.wmbahari.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import warungmakanbahariapi.wmbahari.entity.Customer;
import warungmakanbahariapi.wmbahari.entity.User;
import warungmakanbahariapi.wmbahari.model.request.CustomerRequest;
import warungmakanbahariapi.wmbahari.model.response.CustomerResponse;
import warungmakanbahariapi.wmbahari.model.response.UserResponse;
import warungmakanbahariapi.wmbahari.repository.CustomerRepository;
import warungmakanbahariapi.wmbahari.service.CustomerService;
import warungmakanbahariapi.wmbahari.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerResponse create(CustomerRequest customer) {


        Customer saveCustomerEntity = Customer.builder()
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .build();


        Customer save = customerRepository.save(saveCustomerEntity);

        return CustomerResponse.builder()
                .name(save.getName())
                .phoneNumber(save.getPhoneNumber())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerResponse deleteById(String id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");

        customerRepository.deleteById(byId.get().getName());
        return CustomerResponse.builder()
                .name("Succes Delete User with name :" + byId.get().getName())
                .build();
    }

    @Override
    public CustomerResponse findById(String customer) {
        Optional<Customer> byId = customerRepository.findById(customer);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");

        return CustomerResponse.builder()
                .name(byId.get().getName())
                .phoneNumber(byId.get().getPhoneNumber())
                .build();
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customer) {
        Optional<Customer> byId = customerRepository.findById(customer.getName());
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");

        Customer saveCustomerEntity = Customer.builder()
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .build();

        Customer saveAndFlush = customerRepository.saveAndFlush(saveCustomerEntity);
        return CustomerResponse.builder()
                .name(saveAndFlush.getName())
                .phoneNumber(saveAndFlush.getPhoneNumber())
                .build();
    }
}
