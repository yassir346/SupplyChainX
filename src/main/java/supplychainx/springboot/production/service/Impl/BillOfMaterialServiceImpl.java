package supplychainx.springboot.production.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.production.dto.BillOfMaterialRequest;
import supplychainx.springboot.production.dto.BillOfMaterialResponse;
import supplychainx.springboot.production.mapper.BillOfMaterialMapper;
import supplychainx.springboot.production.model.BillOfMaterial;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.repository.BillOfMaterialRepository;
import supplychainx.springboot.production.repository.ProductRepository;
import supplychainx.springboot.production.service.IBillOfMaterialService;
import supplychainx.springboot.supply.model.RawMaterial;
import supplychainx.springboot.supply.repository.IRawMaterialRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BillOfMaterialServiceImpl implements IBillOfMaterialService {
    private final BillOfMaterialRepository billOfMaterialRepository;
    private final BillOfMaterialMapper billOfMaterialMapper;
    private final ProductRepository productRepository;
    private final IRawMaterialRepository rawMaterialRepository;

    @Override
    public BillOfMaterialResponse create(BillOfMaterialRequest request) {
        validateRequest(request);

        if(billOfMaterialRepository.findByRawMaterialIdAndProductId(request.getRawMaterialId(), request.getProductId()) != null){
            throw new IllegalArgumentException("A BillOfMaterial with the same Product and Raw Material already exists");
        }

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + request.getProductId()));

        RawMaterial rawMaterial = rawMaterialRepository.findById(request.getRawMaterialId())
                .orElseThrow(() -> new EntityNotFoundException("Raw material not found: " + request.getRawMaterialId()));

        BillOfMaterial bom = billOfMaterialMapper.toEntity(request, rawMaterial, product);
        BillOfMaterial savedBom = billOfMaterialRepository.save(bom);
        return billOfMaterialMapper.mapToResponse(savedBom);
    }

    @Override
    public BillOfMaterialResponse update(BillOfMaterialRequest request, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BillOfMaterialResponse findBomById(Long id) {
        return null;
    }

    @Override
    public List<BillOfMaterialResponse> findAllBom() {
        return List.of();
    }

    public void validateRequest(BillOfMaterialRequest request){
        if (request == null) throw new IllegalArgumentException("Request cannot be null");
        if (request.getProductId() == null) throw new IllegalArgumentException("Product ID cannot be null");
        if (request.getRawMaterialId() == null) throw new IllegalArgumentException("Raw Material ID cannot be null");
        if (request.getQuantityPerProduct() <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");
    }

    private void requireValidId(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("Invalid ID");
    }
}
