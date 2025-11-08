package supplychainx.springboot.delivery.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import supplychainx.springboot.common.enums.DeliveryStatus;
import supplychainx.springboot.common.enums.OrderStatus;
import supplychainx.springboot.delivery.dto.DeliveryRequest;
import supplychainx.springboot.delivery.dto.DeliveryResponse;
import supplychainx.springboot.delivery.model.Delivery;
import supplychainx.springboot.delivery.model.Order;
import supplychainx.springboot.delivery.repository.DeliveryRepository;
import supplychainx.springboot.delivery.repository.OrderRepository;
import supplychainx.springboot.delivery.service.DeliveryMapper;
import supplychainx.springboot.delivery.service.IDeliveryService;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryServiceImpl implements IDeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public DeliveryResponse create(DeliveryRequest request) {
        if (request.getCostPerKm() < 0) {
            throw new EntityNotFoundException("Cost per km cannot be less than zero");
        }
        if (request.getDistanceKm() <= 0) {
            throw new EntityNotFoundException("Distance km cannot be less than or equal to zero");
        }

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + request.getOrderId()));

        if (order.getStatus().equals(OrderStatus.ANNULEE)) {
            throw new EntityNotFoundException("Cannot create a delivery for an Order that has been annulled");
        }
        if (order.getDelivery() != null) {
            throw new IllegalStateException("A delivery already exists for this order");
        }

        order.setStatus(OrderStatus.LIVREE);
        orderRepository.save(order);

        double totalCost = request.getDistanceKm() * request.getCostPerKm();

        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setStatus(DeliveryStatus.PLANIFIEE);
        delivery.setCost(totalCost);
        delivery.setDeliveryDate(request.getDeliveryDate());
        delivery.setDriver(request.getDriver());
        delivery.setVehicle(request.getVehicle());

        Delivery saved = deliveryRepository.save(delivery);

        return deliveryMapper.mapToResponse(saved);
    }
}


