package supplychainx.springboot.production.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.production.dto.PdtOrderRequest;
import supplychainx.springboot.production.dto.PdtOrderResponse;
import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.service.IPdtOrderService;

@RestController
@RequestMapping("/production_order")
@AllArgsConstructor
public class PdtOrderController {

    private final IPdtOrderService pdtOrderService;

    @PostMapping("/add")
    public ResponseEntity<PdtOrderResponse> create(@RequestBody PdtOrderRequest pdtOrderRequest){
        PdtOrderResponse response = pdtOrderService.create(pdtOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PdtOrderResponse> update(@RequestBody PdtOrderRequest pdtOrderRequest, @PathVariable Long id){
        PdtOrderResponse response = pdtOrderService.update(pdtOrderRequest, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




}
