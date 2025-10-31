package supplychainx.springboot.supply.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<RawMaterial> create(@RequestBody RawMaterial rawMaterial){
        return ResponseEntity.status(HttpStatus.CREATED).body(rawMaterialService.save(rawMaterial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterial> get(@PathVariable Long id){
        return ResponseEntity.ok(rawMaterialService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RawMaterial>> list(){
        return ResponseEntity.ok(rawMaterialService.findAllRawMaterials());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterial> update(@PathVariable Long id, @RequestBody RawMaterial rawMaterial){
        return ResponseEntity.ok(rawMaterialService.save(rawMaterial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable RawMaterial rawMaterial){
        rawMaterialService.delete(rawMaterial);
        return ResponseEntity.noContent().build();
    }
}
