package warungmakanbahariapi.wmbahari.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warungmakanbahariapi.wmbahari.entity.Customer;
import warungmakanbahariapi.wmbahari.entity.Order;
import warungmakanbahariapi.wmbahari.entity.Table;
import warungmakanbahariapi.wmbahari.model.response.CustomerResponse;
import warungmakanbahariapi.wmbahari.model.response.OrderRequest;
import warungmakanbahariapi.wmbahari.model.response.OrderResponse;

import warungmakanbahariapi.wmbahari.service.CustomerService;
import warungmakanbahariapi.wmbahari.service.OrderService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<?> createOrders(@RequestBody OrderRequest request){


        OrderRequest orderRequest = OrderRequest.builder()
                .productName(request.getProductName())
                .customerId(request.getCustomerId())
                .build();

        CustomerResponse idCustomer = customerService.findById(request.getCustomerId());

        Customer customer = Customer.builder()
                .name(idCustomer.getName())
                .build();
        Order order = Order.builder()
                .tables(new Table())
                .customer(customer)
                .build();
        Order orderSave = orderService.create(order);


        return ResponseEntity.ok(orderSave);
    }

    @GetMapping
    public ResponseEntity<?> getALl(@RequestParam(required = false) OrderRequest request){

        List<Order> all = orderService.getAll(request);

        return ResponseEntity.ok(all);
    }

    @GetMapping
    public ResponseEntity<?> getALlByCustomerid(@RequestParam(required = false) OrderRequest request){
        OrderResponse orderResponse = OrderResponse.builder()
                .customerId(request.getCustomerId())
                .productName(request.getProductName())
                .day(request.getDay())
                .year(request.getYear())
                .month(request.getMonth())
                .startdate(request.getStartdate())
                .endDate(request.getEndDate())
                .transType(request.getTransType())
                .build();

        return ResponseEntity.ok(orderResponse);
    }
    @GetMapping
    public ResponseEntity<?> getCutomerById(@RequestParam(required = false) String request){
        Order byId = orderService.getById(request);

        return ResponseEntity.ok(byId);
    }
}
