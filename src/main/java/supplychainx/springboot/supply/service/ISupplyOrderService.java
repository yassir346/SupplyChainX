package supplychainx.springboot.supply.service;

import supplychainx.springboot.supply.dto.SupplyOrderRequestDTO;
import supplychainx.springboot.supply.dto.SupplyOrderRequestDTO;
import supplychainx.springboot.supply.model.SupplyOrder;

import java.util.List;

public interface ISupplyOrderService {
    SupplyOrder save(SupplyOrderRequestDTO supplyOrderRequest);
    SupplyOrder update(Long id, SupplyOrder supplyOrder);
    int delete(Long id);
    SupplyOrder findById(Long id);
    List<SupplyOrder> getAllSupplyOrders();
}
