package supplychainx.springboot.supply.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import supplychainx.springboot.common.enums.SupplyOrderStatus;
import supplychainx.springboot.supply.dto.SupplyOrderRequestDTO;
import supplychainx.springboot.supply.model.RawMaterial;
import supplychainx.springboot.supply.model.Supplier;
import supplychainx.springboot.supply.model.SupplyOrder;
import supplychainx.springboot.supply.model.SupplyOrderRawMaterial;
import supplychainx.springboot.supply.repository.IRawMaterialRepository;
import supplychainx.springboot.supply.repository.ISupplierRepository;
import supplychainx.springboot.supply.repository.ISupplyOrderRepository;
import supplychainx.springboot.supply.service.ISupplyOrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SupplyOrderServiceImpl implements ISupplyOrderService {
    ISupplyOrderRepository supplyOrderRepository;
    ISupplierRepository supplierRepository;
    IRawMaterialRepository rawMaterialRepository;

    @Override
    public SupplyOrder save(SupplyOrderRequestDTO supplyOrderRequest) {
        if(!supplyOrderRequest.getOrderDate().equals(LocalDate.now())){
            System.out.println("the order must be now");
        }

        Supplier supplier = supplierRepository.findById(supplyOrderRequest.getSupplierId())
                .orElseThrow();
        SupplyOrder supplyOrder = new SupplyOrder();
        supplyOrder.setOrderDate(supplyOrderRequest.getOrderDate());
        supplyOrder.setSupplier(supplier);
        supplyOrder.setStatus(SupplyOrderStatus.EN_ATTENTE);

        List<SupplyOrderRawMaterial> supplyOrderRawMaterialList = new ArrayList<>();

        for(SupplyOrderRequestDTO.RawMaterialQuantity rmq : supplyOrderRequest.getRawMaterials()){

            RawMaterial rawMaterial = rawMaterialRepository.findById(rmq.getRawMaterialId()).orElseThrow();

            SupplyOrderRawMaterial supplyOrderRawMaterial = new SupplyOrderRawMaterial();
            supplyOrderRawMaterial.setRawMaterial(rawMaterial);
            supplyOrderRawMaterial.setQuantity(rmq.getQuantity());
            supplyOrderRawMaterial.setSupplyOrder(supplyOrder);

            supplyOrderRawMaterialList.add(supplyOrderRawMaterial);
        }

        supplyOrder.setSupplyOrderRawMaterialList(supplyOrderRawMaterialList);
        return supplyOrderRepository.save(supplyOrder);
    }

    @Override
    public SupplyOrder update(Long id, SupplyOrder supplyOrder) {
        return null;
    }

    @Override
    public int delete(Long id) {
        SupplyOrder foundSupplyOrder = supplyOrderRepository.findById(id).orElseThrow();

        if(foundSupplyOrder.getStatus() != SupplyOrderStatus.RECUE){
            supplyOrderRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public SupplyOrder findById(Long id) {
        return supplyOrderRepository.findById(id).orElse(null);
    }

    @Override
    public List<SupplyOrder> getAllSupplyOrders() {
        return supplyOrderRepository.findAll();
    }
}
