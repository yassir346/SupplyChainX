package supplychainx.springboot.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supplychainx.springboot.supply.model.SupplyOrderRawMaterial;

@Repository
public interface ISupplyOrderRawMaterialRepository extends JpaRepository<SupplyOrderRawMaterial, Long> {
}
