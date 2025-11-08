package supplychainx.springboot.delivery.service;

import org.springframework.stereotype.Service;
import supplychainx.springboot.delivery.dto.DeliveryResponse;
import supplychainx.springboot.delivery.model.Delivery;

@Service
public class DeliveryMapper {
    public DeliveryResponse mapToResponse(Delivery delivery) {
        DeliveryResponse response = new DeliveryResponse();
        response.setId(delivery.getId());
        response.setOrderId(delivery.getOrder().getId());
        response.setTotalCost(delivery.getCost());
        response.setDeliveryDate(delivery.getDeliveryDate());
        response.setDriver(delivery.getDriver());
        response.setStatus(delivery.getStatus().name());
        response.setVehicle(delivery.getVehicle());
        return response;
    }
}
