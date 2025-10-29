package supplychainx.springboot.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supplychainx.springboot.supply.model.RawMaterial;

@Repository
public interface IRawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
