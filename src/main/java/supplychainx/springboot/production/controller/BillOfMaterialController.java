package supplychainx.springboot.production.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.production.dto.BillOfMaterialRequest;
import supplychainx.springboot.production.dto.BillOfMaterialResponse;
import supplychainx.springboot.production.dto.PdtOrderResponse;
import supplychainx.springboot.production.model.BillOfMaterial;
import supplychainx.springboot.production.service.IBillOfMaterialService;

@RestController
@RequestMapping("/bill_of_material")
@AllArgsConstructor
public class BillOfMaterialController {
    private final IBillOfMaterialService billOfMaterialService;

    @PostMapping("/add")
    public ResponseEntity<BillOfMaterialResponse> create(@RequestBody BillOfMaterialRequest bomRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(billOfMaterialService.create(bomRequest));
    }
}
