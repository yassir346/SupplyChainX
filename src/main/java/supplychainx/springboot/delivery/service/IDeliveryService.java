package supplychainx.springboot.delivery.service;

import supplychainx.springboot.delivery.dto.DeliveryRequest;
import supplychainx.springboot.delivery.dto.DeliveryResponse;

public interface IDeliveryService {
    DeliveryResponse create(DeliveryRequest deliveryRequest);
}
