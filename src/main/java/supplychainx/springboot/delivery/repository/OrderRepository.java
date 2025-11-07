package supplychainx.springboot.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supplychainx.springboot.delivery.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
