package supplychainx.springboot.production.service;

import supplychainx.springboot.production.dto.PdtOrderRequest;
import supplychainx.springboot.production.dto.PdtOrderResponse;

import java.util.List;


public interface IPdtOrderService {
    PdtOrderResponse create(PdtOrderRequest pdtOrderRequest);
    PdtOrderResponse update(PdtOrderRequest pdtOrderRequest, Long id);
    PdtOrderResponse findById(Long id);
    List<PdtOrderResponse> findAllProductionOrders();
    void delete(Long id);
    void cancel(Long id);
}
