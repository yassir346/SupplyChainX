package supplychainx.springboot.supply.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.supply.model.RawMaterial;

import java.util.List;


public interface IRawMaterialService {
    RawMaterial save(RawMaterial rawMaterial);
    RawMaterial update(Long id, RawMaterial rawMaterial);
    void delete(RawMaterial rawMaterial);
    List<RawMaterial> findAllRawMaterials();
    RawMaterial findById(Long id);
    List<RawMaterial> findByStock(int stock);
}
