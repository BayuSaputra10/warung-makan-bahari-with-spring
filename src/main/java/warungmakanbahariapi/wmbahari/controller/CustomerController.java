package warungmakanbahariapi.wmbahari.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warungmakanbahariapi.wmbahari.entity.Customer;
import warungmakanbahariapi.wmbahari.entity.User;
import warungmakanbahariapi.wmbahari.model.request.CustomerRequest;
import warungmakanbahariapi.wmbahari.model.response.CustomerResponse;
import warungmakanbahariapi.wmbahari.repository.CustomerRepository;
import warungmakanbahariapi.wmbahari.service.CustomerService;
import warungmakanbahariapi.wmbahari.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam(required = false) String name){

        List<Customer> all = customerService.getAll();
        customerRepository.findByName(name);


        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(all.get(0).getName())
                .phoneNumber(all.get(1).getPhoneNumber())
                .build();
        return ResponseEntity.ok(all);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestParam(required = false) String menu,
                                                            @PathVariable String id){

        CustomerResponse byId = customerService.findById(id);

        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(byId.getName())
                .phoneNumber(byId.getPhoneNumber())
                .build();
        return ResponseEntity.ok(customerResponse);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request){


        CustomerResponse response = customerService.create(request);

        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(response.getName())
                .phoneNumber(response.getPhoneNumber())
                .build();
        return ResponseEntity.ok(customerResponse);
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest request){

        CustomerResponse response = customerService.updateCustomer(request);

        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(response.getName())
                .phoneNumber(response.getPhoneNumber())
                .build();
        return ResponseEntity.ok(customerResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomerById(@RequestParam(required = false) String menu,
                                                            @PathVariable String id){

        CustomerResponse byId = customerService.deleteById(id);

        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(byId.getName())
                .phoneNumber(byId.getPhoneNumber())
                .build();
        return ResponseEntity.ok(customerResponse);
    }

}
