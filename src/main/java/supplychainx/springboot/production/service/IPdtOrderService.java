package supplychainx.springboot.production.service;

import org.springframework.stereotype.Service;
import supplychainx.springboot.production.dto.PdtOrderRequest;
import supplychainx.springboot.production.dto.PdtOrderResponse;
import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.model.ProductionOrder;

import java.util.List;


public interface IPdtOrderService {
    PdtOrderResponse create(PdtOrderRequest pdtOrderRequest);
    PdtOrderResponse update(PdtOrderRequest pdtOrderRequest, Long id);
    PdtOrderResponse findById(Long id);
    List<Product> findAllProducts();
    void delete(Long id);
}
