package supplychainx.springboot.delivery.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.delivery.dto.CustomerRequestDto;
import supplychainx.springboot.delivery.dto.CustomerResponseDto;
import supplychainx.springboot.delivery.model.Customer;
import supplychainx.springboot.delivery.service.ICustomerService;
import supplychainx.springboot.production.dto.ProductRequestDto;
import supplychainx.springboot.production.dto.ProductResponseDto;
import supplychainx.springboot.production.model.Product;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerRequestDto customerRequest){
        CustomerResponseDto response = customerService.save(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponseDto> update(@PathVariable Long id, @RequestBody CustomerRequestDto customerRequest){
        CustomerResponseDto updatedCustomer = customerService.update(customerRequest, id);
        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findById(id));
    }


    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDto>> getAllProducts(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
