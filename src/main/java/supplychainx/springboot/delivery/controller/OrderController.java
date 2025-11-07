package supplychainx.springboot.delivery.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import supplychainx.springboot.delivery.service.IOrderService;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @PostMapping

}
