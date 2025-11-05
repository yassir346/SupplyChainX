package supplychainx.springboot.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supplychainx.springboot.production.model.ProductionOrder;

public interface PdtOrderReposetory extends JpaRepository<ProductionOrder, Long> {

}
