package supplychainx.springboot.supply.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.supply.dto.RawMaterialRequestDto;
import supplychainx.springboot.supply.model.RawMaterial;
import supplychainx.springboot.supply.repository.IRawMaterialRepository;
import supplychainx.springboot.supply.service.IRawMaterialService;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RawMaterialServiceImpl implements IRawMaterialService {
    IRawMaterialRepository rawMaterialRepository;

    @Override
    public RawMaterial save(RawMaterialRequestDto rawMaterialRequest) {
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setName(rawMaterialRequest.getName());
        rawMaterial.setStock(rawMaterialRequest.getStock());
        rawMaterial.setStockMin(rawMaterialRequest.getStockMin());
        rawMaterial.setUnit(rawMaterialRequest.getUnit());
        return rawMaterialRepository.save(rawMaterial);
    }

    @Override
    public RawMaterial update(Long id, RawMaterial rawMaterial) {
        RawMaterial foundRawMaterial = findById(id);
        if(foundRawMaterial == null){
            System.out.println("the rawMaterial object not found");
            return null;
        }
        foundRawMaterial.setName(rawMaterial.getName());
        foundRawMaterial.setStock(rawMaterial.getStock());
        foundRawMaterial.setStockMin(rawMaterial.getStockMin());
        foundRawMaterial.setUnit(rawMaterial.getUnit());
        return rawMaterialRepository.save(foundRawMaterial);
    }

    @Override
    public void delete(Long id) {
        rawMaterialRepository.deleteById(id);
    }

    @Override
    public List<RawMaterial> findAllRawMaterials() {
        List<RawMaterial> rawMaterialList = rawMaterialRepository.findAll();
        return rawMaterialList;
    }

    @Override
    public RawMaterial findById(Long id) {
        return rawMaterialRepository.findById(id).orElse(null);
    }

    @Override
    public List<RawMaterial> findByStock(int stockMin) {
        List<RawMaterial> rawMaterialList = rawMaterialRepository.findByStockLessThan(stockMin);
        return rawMaterialList;
    }
}
