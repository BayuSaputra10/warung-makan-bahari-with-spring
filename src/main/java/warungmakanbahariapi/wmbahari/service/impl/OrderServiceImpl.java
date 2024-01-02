package warungmakanbahariapi.wmbahari.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import warungmakanbahariapi.wmbahari.entity.Order;
import warungmakanbahariapi.wmbahari.model.response.CustomerResponse;
import warungmakanbahariapi.wmbahari.model.response.OrderRequest;
import warungmakanbahariapi.wmbahari.model.response.OrderResponse;
import warungmakanbahariapi.wmbahari.repository.OrderRepository;
import warungmakanbahariapi.wmbahari.service.CustomerService;
import warungmakanbahariapi.wmbahari.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerService customerService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order create(Order request) {
        Order order = Order.builder()
                .tables(request.getTables())
                .customer(request.getCustomer())
                .trans_dates(request.getTrans_dates())
                .build();
        return orderRepository.save(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Order> getAll(OrderRequest request) {

        Specification<Order> orderSpecification = getOrderSpecification(request);
        return orderRepository.findAll(orderSpecification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderResponse getAllByCustomerId(OrderRequest request) {
        CustomerResponse CustomerId = customerService.findById(request.getCustomerId());
        OrderResponse orderResponse = OrderResponse.builder()
                .customerId(CustomerId.getUserId())
                .productName(request.getProductName())
                .day(request.getDay())
                .year(request.getYear())
                .month(request.getMonth())
                .startdate(request.getStartdate())
                .endDate(request.getEndDate())
                .transType(request.getTransType())
                .build();

        return orderResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order getById(String id) {
        Optional<Order> byId = orderRepository.findById(id);
        return byId.orElseThrow();
    }

    private Specification<Order> getOrderSpecification(OrderRequest request) {
        Specification<Order> specification = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (request.getCustomerId() != null){
                Predicate addCustomerId =  criteriaBuilder.equal(root.get("customerId"), request.getCustomerId());
                predicates.add(addCustomerId);
            }
            if (request.getYear() != null){
                Predicate addYear =  criteriaBuilder.equal(root.get("yaer") ,request.getYear());
                predicates.add(addYear);
            }
            if (request.getProductName() != null){
                Predicate addProductName =  criteriaBuilder.like(root.get("productName"),"%"+ request.getProductName() + "%");
                predicates.add(addProductName);
            }if (request.getDay() != null){
                Predicate addDay =  criteriaBuilder.like(root.get("day"),"%"+ request.getDay() + "%");
                predicates.add(addDay);
            }if (request.getMonth() != null){
                Predicate addMonth =  criteriaBuilder.like(root.get("month"),"%"+ request.getMonth() + "%");
                predicates.add(addMonth);
            }if (request.getYear() != null){
                Predicate addyear =  criteriaBuilder.like(root.get("year"),"%"+ request.getYear() + "%");
                predicates.add(addyear);
            }if (request.getStartdate() != null){
                Predicate addStartDate =  criteriaBuilder.like(root.get("startDate"),"%"+ request.getStartdate() + "%");
            predicates.add(addStartDate);
            }if (request.getEndDate() != null){
                Predicate addEndDate =  criteriaBuilder.like(root.get("endDate"),"%"+ request.getEndDate() + "%");
            predicates.add(addEndDate);
            }if (request.getTransType() != null){
                Predicate addTransType =  criteriaBuilder.like(root.get("transtype"),"%"+ request.getTransType() + "%");
            predicates.add(addTransType);
            }

            return query.where(predicates.toArray(new Predicate[] {})).getRestriction();
        };
        return specification;
    }
}
