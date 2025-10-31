package supplychainx.springboot.supply.service;

import supplychainx.springboot.supply.dto.SupplyRequestDTO;
import supplychainx.springboot.supply.model.Supplier;
import supplychainx.springboot.supply.repository.ISupplierRepository;

public class SupplierMapper {
    public static Supplier toEntity(SupplyRequestDTO dto){
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setRating(dto.getRating());
        supplier.setRawMaterials(dto.getRawMaterialList());
        return supplier;
    }
}
