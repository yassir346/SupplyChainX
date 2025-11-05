package supplychainx.springboot.production.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.common.enums.ProductionOrderStatus;
import supplychainx.springboot.production.dto.PdtOrderRequest;
import supplychainx.springboot.production.dto.PdtOrderResponse;
import supplychainx.springboot.production.mapper.ProductionOrderMapper;
import supplychainx.springboot.production.model.BillOfMaterial;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.model.ProductionOrder;
import supplychainx.springboot.production.repository.PdtOrderReposetory;
import supplychainx.springboot.production.repository.ProductRepository;
import supplychainx.springboot.production.service.IPdtOrderService;
import supplychainx.springboot.supply.model.RawMaterial;
import supplychainx.springboot.supply.repository.IRawMaterialRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PdtOrderServiceImpl implements IPdtOrderService {
    private final PdtOrderReposetory pdtOrderReposetory;
    private final ProductRepository productRepository;
    private final IRawMaterialRepository rawMaterialRepository;
    private final ProductionOrderMapper productionOrderMapper;

    @Override
    public PdtOrderResponse create(PdtOrderRequest pdtOrderRequest) {
        Product product = validateProduct(pdtOrderRequest);
        setNewBillOfMaterials(pdtOrderRequest, product);

        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setProduct(product);
        productionOrder.setQuantity(pdtOrderRequest.getQuantity());
        productionOrder.setStatus(ProductionOrderStatus.valueOf(pdtOrderRequest.getStatus()));
        productionOrder.setEndDate(pdtOrderRequest.getEndDate());
        productionOrder.setStartDate(pdtOrderRequest.getStartDate());

        ProductionOrder savedProductionOrder = pdtOrderReposetory.save(productionOrder);
        return productionOrderMapper.mapToResponse(savedProductionOrder);
    }

    @Override
    public PdtOrderResponse update(PdtOrderRequest pdtOrderRequest, Long id) {
        validateProduct(pdtOrderRequest);
        ProductionOrder foundProductionOrder = pdtOrderReposetory.findById(id).orElseThrow();

        Product validatedProduct = validateProduct(pdtOrderRequest);

        if(!foundProductionOrder.getProduct().getId().equals(validatedProduct.getId()) || foundProductionOrder.getQuantity() != pdtOrderRequest.getQuantity()){

            restoreOldBillOfMaterials(foundProductionOrder);
            setNewBillOfMaterials(pdtOrderRequest, validatedProduct);
            foundProductionOrder.setProduct(validatedProduct);
            foundProductionOrder.setQuantity(pdtOrderRequest.getQuantity());
        }
        foundProductionOrder.setStartDate(pdtOrderRequest.getStartDate());
        foundProductionOrder.setEndDate(pdtOrderRequest.getEndDate());
        foundProductionOrder.setStatus(ProductionOrderStatus.valueOf(pdtOrderRequest.getStatus()));

        ProductionOrder updatedPdtOrder = pdtOrderReposetory.save(foundProductionOrder);
        return productionOrderMapper.mapToResponse(updatedPdtOrder);
    }

    @Override
    public PdtOrderResponse findById(Long id) {
        ProductionOrder foundProductionOrder = pdtOrderReposetory.findById(id).orElseThrow();
        return productionOrderMapper.mapToResponse(foundProductionOrder);
    }

    @Override
    public List<ProductionOrder> findAllProducts() {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        ProductionOrder foundProductionOrder = pdtOrderReposetory.findById(id).orElseThrow();
        if (foundProductionOrder.getStatus() == ProductionOrderStatus.TERMINE || foundProductionOrder.getStatus() == ProductionOrderStatus.EN_PRODUCTION){
            throw new IllegalStateException("Cannot delete a production order that is already in progress or completed");
        }
        restoreOldBillOfMaterials(foundProductionOrder);
        pdtOrderReposetory.delete(foundProductionOrder);
    }


    private Product validateProduct(PdtOrderRequest pdtOrderRequest){
        Product foundProduct = productRepository.findById(pdtOrderRequest.getProductId()).orElseThrow();

        if(pdtOrderRequest.getQuantity() <= 0){
            throw new IllegalArgumentException("Production order quantity must be greater than zero");
        }
        if(pdtOrderRequest.getStartDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Production order start date cannot be in the past");
        }
        if (pdtOrderRequest.getEndDate().isBefore(pdtOrderRequest.getStartDate())){
            throw new IllegalArgumentException("Production order end date cannot be before start date");
        }

        return foundProduct;
    }

    private static List<BillOfMaterial> getBillOfMaterials(PdtOrderRequest pdtOrderRequest, Product product){
        List<BillOfMaterial> bomList = product.getBillOfMaterials();
        if(bomList.isEmpty()){
            throw new IllegalStateException("No Bill of Materials found for product: " + product.getName());
        }

        for(BillOfMaterial bom : bomList){
            RawMaterial raw = bom.getRawMaterial();
            int requiredQty = bom.getQuantity() * pdtOrderRequest.getQuantity();

            if(raw.getStock() < requiredQty){
                throw new IllegalStateException("Insufficient stock for raw material: " + raw.getName() +
                        " (required: " + requiredQty + ", available: " + raw.getStock() + ")");
            }
        }
        return bomList;
    }

    private void setNewBillOfMaterials(PdtOrderRequest pdtOrderRequest, Product product){
        List<BillOfMaterial> bomList = getBillOfMaterials(pdtOrderRequest, product);

        for(BillOfMaterial bom : bomList){
            RawMaterial raw = bom.getRawMaterial();
            int requiredQty = bom.getQuantity() * pdtOrderRequest.getQuantity();
            raw.setStock(raw.getStock() - requiredQty);
            rawMaterialRepository.save(raw);
        }
    }

    private void restoreOldBillOfMaterials(ProductionOrder productionOrder){
        List<BillOfMaterial> bomList = productionOrder.getProduct().getBillOfMaterials();
        for(BillOfMaterial bom : bomList){
            RawMaterial rawMaterial = bom.getRawMaterial();
            int quantityToRestore = bom.getQuantity() * productionOrder.getQuantity();
            rawMaterial.setStock(rawMaterial.getStock() + quantityToRestore);
            rawMaterialRepository.save(rawMaterial);
        }
    }
}
