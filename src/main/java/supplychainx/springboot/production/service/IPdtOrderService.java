package supplychainx.springboot.production.service;

import supplychainx.springboot.production.dto.PdtOrderRequest;
import supplychainx.springboot.production.dto.PdtOrderResponse;
import supplychainx.springboot.production.model.ProductionOrder;

import java.util.List;


public interface IPdtOrderService {
    PdtOrderResponse create(PdtOrderRequest pdtOrderRequest);
    PdtOrderResponse update(PdtOrderRequest pdtOrderRequest, Long id);
    PdtOrderResponse findById(Long id);
    List<ProductionOrder> findAllProducts();
    void delete(Long id);
}
