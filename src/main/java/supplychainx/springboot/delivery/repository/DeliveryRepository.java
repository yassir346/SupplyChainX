package supplychainx.springboot.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supplychainx.springboot.delivery.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
