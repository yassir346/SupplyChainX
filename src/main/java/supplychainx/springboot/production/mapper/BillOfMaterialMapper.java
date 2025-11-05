package supplychainx.springboot.production.mapper;

import org.springframework.stereotype.Service;
import supplychainx.springboot.production.dto.BillOfMaterialRequest;
import supplychainx.springboot.production.dto.BillOfMaterialResponse;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.model.BillOfMaterial;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.supply.model.RawMaterial;

@Service
public class BillOfMaterialMapper {

    public BillOfMaterialResponse mapToResponse(BillOfMaterial billOfMaterial){
        BillOfMaterialResponse response = new BillOfMaterialResponse();
        response.setBillOfMaterialId(billOfMaterial.getId());
        response.setRawMaterialName(billOfMaterial.getRawMaterial().getName());
        response.setRawMaterialStock((long) billOfMaterial.getRawMaterial().getStock());
        response.setProductName(billOfMaterial.getProduct().getName());
        response.setProductStock((long) billOfMaterial.getProduct().getStock());
        response.setQuantityTypePerProduct(billOfMaterial.getQuantity());
        return response;
    }

    public BillOfMaterial toEntity(BillOfMaterialRequest billOfMaterialRequest,
                                   RawMaterial rawMaterial, Product product) {
        BillOfMaterial billOfMaterial = new BillOfMaterial();
        billOfMaterial.setQuantity(billOfMaterialRequest.getQuantityPerProduct());
        billOfMaterial.setProduct(product);
        billOfMaterial.setRawMaterial(rawMaterial);

        return billOfMaterial;
    }

    public ProductResponseDto mapProduct(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCost(product.getCost());
        dto.setStock(product.getStock());
        dto.setProductionTime(product.getProductionTime());
        return dto;
    }


}
