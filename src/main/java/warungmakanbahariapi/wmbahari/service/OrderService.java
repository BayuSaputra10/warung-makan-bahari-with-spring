package warungmakanbahariapi.wmbahari.service;

import warungmakanbahariapi.wmbahari.entity.Order;
import warungmakanbahariapi.wmbahari.model.response.OrderRequest;
import warungmakanbahariapi.wmbahari.model.response.OrderResponse;

import java.util.List;

public interface OrderService {

    Order create(Order request);

    List<Order> getAll(OrderRequest request);

    OrderResponse getAllByCustomerId(OrderRequest request);

    Order getById(String id);
}
