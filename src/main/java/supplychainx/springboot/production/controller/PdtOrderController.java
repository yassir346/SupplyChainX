package supplychainx.springboot.production.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.common.enums.Role;
import supplychainx.springboot.production.dto.PdtOrderRequest;
import supplychainx.springboot.production.dto.PdtOrderResponse;
import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.service.IPdtOrderService;
import supplychainx.springboot.security.SecuredAction;

import java.util.List;

@RestController
@RequestMapping("/production_order")
@AllArgsConstructor
public class PdtOrderController {

    private final IPdtOrderService pdtOrderService;

    @PostMapping("/add")
    @SecuredAction(roles = {Role.CHEF_PRODUCTION})
    public ResponseEntity<PdtOrderResponse> create(@RequestBody PdtOrderRequest pdtOrderRequest){
        PdtOrderResponse response = pdtOrderService.create(pdtOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    @SecuredAction(roles = {Role.CHEF_PRODUCTION})
    public ResponseEntity<PdtOrderResponse> update(@RequestBody PdtOrderRequest pdtOrderRequest, @PathVariable Long id){
        PdtOrderResponse response = pdtOrderService.update(pdtOrderRequest, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @SecuredAction(roles = {Role.SUPERVISEUR_PRODUCTION})
    public ResponseEntity<PdtOrderResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(pdtOrderService.findById(id));
    }

    @GetMapping("/")
    @SecuredAction(roles = {Role.SUPERVISEUR_PRODUCTION})
    public ResponseEntity<List<PdtOrderResponse>> getAll(){
        return ResponseEntity.ok(pdtOrderService.findAllProductionOrders());
    }

    @DeleteMapping("/{id}")
    @SecuredAction(roles = {Role.CHEF_PRODUCTION})
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pdtOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancel/{id}")
    @SecuredAction(roles = {Role.CHEF_PRODUCTION})
    public ResponseEntity<Void> cancel(@PathVariable Long id){
        pdtOrderService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
