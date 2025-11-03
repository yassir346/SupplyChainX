package supplychainx.springboot.supply.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.supply.dto.SupplyOrderRequestDTO;
import supplychainx.springboot.supply.dto.SupplyOrderResponseDTO;
import supplychainx.springboot.supply.model.SupplyOrder;
import supplychainx.springboot.supply.service.ISupplyOrderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/supply_order")
public class SupplyOrderController {
    private ISupplyOrderService supplyOrderService;

    @PostMapping("/add")
    public ResponseEntity<SupplyOrderResponseDTO> create(@RequestBody SupplyOrderRequestDTO supplyOrderRequest){
        SupplyOrder createdSupplyOrder = supplyOrderService.save(supplyOrderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(supplyOrderService.toResponse(createdSupplyOrder));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SupplyOrderResponseDTO> update(@RequestBody SupplyOrderRequestDTO supplyOrderRequest, @PathVariable Long id){
        SupplyOrder updatedSupplyOrder = supplyOrderService.update(id, supplyOrderRequest);
        return ResponseEntity.ok(supplyOrderService.toResponse(updatedSupplyOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyOrder> get(@PathVariable Long id){
        return ResponseEntity.ok(supplyOrderService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<SupplyOrder>> getAll(){
        return ResponseEntity.ok(supplyOrderService.getAllSupplyOrders());
    }


}
