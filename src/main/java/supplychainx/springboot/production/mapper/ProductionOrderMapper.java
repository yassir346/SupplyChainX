package supplychainx.springboot.production.mapper;

import org.springframework.stereotype.Service;
import supplychainx.springboot.production.dto.PdtOrderResponse;
import supplychainx.springboot.production.model.ProductionOrder;

import java.util.List;

@Service
public class ProductionOrderMapper {

    public PdtOrderResponse mapToResponse(ProductionOrder productionOrder){
        PdtOrderResponse dto = new PdtOrderResponse();
        dto.setId(productionOrder.getId());
        dto.setStatus(productionOrder.getStatus().name());
        dto.setQuantity(productionOrder.getQuantity());
        dto.setStartDate(productionOrder.getStartDate());
        dto.setEndDate(productionOrder.getEndDate());
        dto.setProductId(productionOrder.getProduct().getId());
        dto.setProductName(productionOrder.getProduct().getName());
        dto.setProductCost(productionOrder.getProduct().getCost());
        dto.setProductionEstimatedTime(productionOrder.getQuantity() * productionOrder.getProduct().getProductionTime());

        List<PdtOrderResponse.BillOfMaterialResponse> bomResponses = productionOrder.getProduct().getBillOfMaterials()
                .stream()
                .map(bom -> {
                    PdtOrderResponse.BillOfMaterialResponse bomDto = new PdtOrderResponse.BillOfMaterialResponse();
                    bomDto.setRawMaterialId(bom.getId());
                    bomDto.setRawMaterialName(bomDto.getRawMaterialName());
                    bomDto.setCurrentStock(bom.getRawMaterial().getStock());
                    bomDto.setQuantityPerUnit(bom.getQuantity());
                    bomDto.setTotalQuantityNeeded(bom.getQuantity() * productionOrder.getQuantity());

                    return bomDto;
                })
                .toList();
        dto.setBillOfMaterials(bomResponses);

        return dto;
    }


}
