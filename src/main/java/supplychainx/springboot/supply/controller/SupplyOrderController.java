package supplychainx.springboot.supply.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.supply.dto.SupplyOrderRequestDTO;
import supplychainx.springboot.supply.model.SupplyOrder;
import supplychainx.springboot.supply.service.ISupplyOrderService;

@RestController
@AllArgsConstructor
@RequestMapping("/supply_order")
public class SupplyOrderController {
    private ISupplyOrderService supplyOrderService;

    @PostMapping("/add")
    public ResponseEntity<SupplyOrder> create(@RequestBody SupplyOrderRequestDTO supplyOrderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyOrderService.save(supplyOrderRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SupplyOrder> update(@RequestBody SupplyOrderRequestDTO supplyOrderRequest, @PathVariable Long id){
        return ResponseEntity.ok(supplyOrderService.update(id, supplyOrderRequest));
    }


}
