package supplychainx.springboot.supply.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.common.enums.Role;
//import supplychainx.springboot.security.SecuredAction;
import supplychainx.springboot.supply.dto.RawMaterialRequestDto;
import supplychainx.springboot.supply.model.RawMaterial;
import supplychainx.springboot.supply.service.IRawMaterialService;

import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping("/rawMaterial")
@AllArgsConstructor
public class RawMaterialController {
    IRawMaterialService rawMaterialService;

    @PostMapping("/add")
//    @SecuredAction(roles = {Role.GESTIONNAIRE_APPROVISIONNEMENT})
    public ResponseEntity<RawMaterial> create(@RequestBody RawMaterialRequestDto rawMaterialRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(rawMaterialService.save(rawMaterialRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterial> get(@PathVariable Long id){
        return ResponseEntity.ok(rawMaterialService.findById(id));
    }

    @GetMapping
//    @SecuredAction(roles = {Role.SUPERVISEUR_LOGISTIQUE})
    public ResponseEntity<List<RawMaterial>> list(){
        return ResponseEntity.ok(rawMaterialService.findAllRawMaterials());
    }

    @PutMapping("/{id}")
//    @SecuredAction(roles = {Role.GESTIONNAIRE_APPROVISIONNEMENT})
    public ResponseEntity<RawMaterial> update(@PathVariable Long id, @RequestBody RawMaterial rawMaterial){
        return ResponseEntity.ok(rawMaterialService.update(id, rawMaterial));
    }

    @DeleteMapping("/{id}")
//    @SecuredAction(roles = {Role.GESTIONNAIRE_APPROVISIONNEMENT})
    public ResponseEntity<Void> delete(@PathVariable Long id){
        rawMaterialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
