package supplychainx.springboot.supply.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.supply.dto.SupplyRequestDTO;
import supplychainx.springboot.supply.model.Supplier;
import supplychainx.springboot.supply.service.Impl.SupplierServiceImpl;
import supplychainx.springboot.supply.service.SupplierMapper;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierServiceImpl supplierService;

    @PostMapping ("/add")
    public ResponseEntity<Supplier> create(@RequestBody SupplyRequestDTO supplierDTO){
        Supplier supplier = SupplierMapper.toEntity(supplierDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.save(supplier));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> get(@PathVariable Long id){
        return ResponseEntity.ok(supplierService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Long id, @RequestBody Supplier supplier){
        Supplier updatedSupplier = supplierService.update(supplier, id);
        return ResponseEntity.ok(updatedSupplier);
    }

    @GetMapping("/")
    public ResponseEntity<List<Supplier>> list(){
        return ResponseEntity.ok(supplierService.findAllSuppliers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
