package supplychainx.springboot.delivery.service;

import supplychainx.springboot.delivery.dto.CustomerRequestDto;
import supplychainx.springboot.delivery.dto.CustomerResponseDto;
import supplychainx.springboot.delivery.dto.OrderRequest;
import supplychainx.springboot.delivery.dto.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse save(OrderRequest orderRequest);
    OrderResponse update(OrderRequest orderRequest, Long id);
    void delete(Long id);
    OrderResponse findById(Long id);
    List<OrderResponse> findAllOrders();
}
