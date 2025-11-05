package supplychainx.springboot.supply.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.supply.dto.RawMaterialRequestDto;
import supplychainx.springboot.supply.model.RawMaterial;

import java.util.List;


public interface IRawMaterialService {
    RawMaterial save(RawMaterialRequestDto rawMaterialReques);
    RawMaterial update(Long id, RawMaterial rawMaterial);
    void delete(Long id);
    List<RawMaterial> findAllRawMaterials();
    RawMaterial findById(Long id);
    List<RawMaterial> findByStock(int stock);
}
