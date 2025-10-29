package supplychainx.springboot.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supplychainx.springboot.supply.model.SupplyOrder;

import java.time.LocalDate;

@Repository
public interface ISupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {
    SupplyOrder findSupplyOrderByOrderDate(LocalDate date);
}
