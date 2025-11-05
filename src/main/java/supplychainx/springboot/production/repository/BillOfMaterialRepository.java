package supplychainx.springboot.production.repository;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supplychainx.springboot.production.model.BillOfMaterial;
import supplychainx.springboot.supply.model.RawMaterial;

import java.util.List;

@Repository
public interface BillOfMaterialRepository extends JpaRepository<BillOfMaterial, Long> {
    List<BillOfMaterial> findByProductId(Long productId);
    BillOfMaterial findByRawMaterialIdAndProductId(Long rawMaterialId, Long productId);
}
