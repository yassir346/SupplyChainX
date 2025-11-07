package supplychainx.springboot.delivery.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.delivery.dto.CustomerResponseDto;
import supplychainx.springboot.delivery.dto.OrderRequest;
import supplychainx.springboot.delivery.dto.OrderResponse;
import supplychainx.springboot.delivery.service.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponse> update(@RequestBody OrderRequest orderRequest, @PathVariable Long id){
        return ResponseEntity.ok(orderService.save(orderRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }


    @GetMapping("/")
    public ResponseEntity<List<OrderResponse>> getAllProducts(){
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
