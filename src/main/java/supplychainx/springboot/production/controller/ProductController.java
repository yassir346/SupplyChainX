package supplychainx.springboot.production.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final IProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto productRequest){
        ProductResponseDto response = productService.create(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody ProductRequestDto productRequest){
        Product updatedProduct = productService.update(productRequest, id);
        ProductResponseDto response = productService.toResponse(updatedProduct);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }


    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
