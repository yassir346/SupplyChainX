package supplychainx.springboot.production.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.common.enums.ProductionOrderStatus;
import supplychainx.springboot.production.controller.PdtOrderController;
import supplychainx.springboot.production.controller.ProductController;
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
        return null;
    }

    @Override
    public PdtOrderResponse findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAllProducts() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

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


}
