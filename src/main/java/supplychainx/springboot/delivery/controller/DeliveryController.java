package supplychainx.springboot.delivery.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import supplychainx.springboot.delivery.dto.DeliveryRequest;
import supplychainx.springboot.delivery.dto.DeliveryResponse;
import supplychainx.springboot.delivery.service.IDeliveryService;
import supplychainx.springboot.delivery.service.IOrderService;

@RestController
@RequestMapping("/deliveries")
@AllArgsConstructor
public class DeliveryController {
    private final IDeliveryService deliveryService;

    @PostMapping("/add")
    public ResponseEntity<DeliveryResponse> create(DeliveryRequest request){
        return ResponseEntity.ok(deliveryService.create(request));
    }
}
