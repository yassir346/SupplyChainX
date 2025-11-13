package supplychainx.springboot.delivery.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.common.enums.OrderStatus;
import supplychainx.springboot.delivery.dto.OrderRequest;
import supplychainx.springboot.delivery.dto.OrderResponse;
import supplychainx.springboot.delivery.model.Customer;
import supplychainx.springboot.delivery.model.Order;
import supplychainx.springboot.delivery.repository.CustomerRepository;
import supplychainx.springboot.delivery.repository.OrderRepository;
import supplychainx.springboot.delivery.service.IOrderService;
import supplychainx.springboot.delivery.service.OrderMapper;
import supplychainx.springboot.production.dto.PdtOrderRequest;
import supplychainx.springboot.production.model.Product;
import supplychainx.springboot.production.model.ProductionOrder;
import supplychainx.springboot.production.repository.ProductRepository;
import supplychainx.springboot.production.service.IPdtOrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final IPdtOrderService pdtOrderService;
    private final OrderMapper orderMapper;


    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = new Order();
        order.setStatus(OrderStatus.EN_PREPARATION);
        order.setQuantity(orderRequest.getQuantity());

        Customer customer = customerRepository.findById(orderRequest.getCustomerId()).orElseThrow();

        order.setCustomer(customer);

        Product product = productRepository.findById(orderRequest.getProductId()).orElseThrow();

        if (product.getStock() < orderRequest.getQuantity()) {
            throw new IllegalStateException("Product doesn't have enough stock: " +
                    product.getStock() + " - Quantity ordered: " + orderRequest.getQuantity());
        }

        product.setStock(product.getStock() - orderRequest.getQuantity());
        productRepository.save(product);
        order.setProduct(product);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse update(OrderRequest orderRequest, Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow();

        if (existingOrder.getStatus() == OrderStatus.LIVREE) {
            throw new IllegalStateException("Cannot update an order that is already delivered");
        }

        if (orderRequest.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        OrderStatus newStatus = OrderStatus.valueOf(orderRequest.getStatus());
        existingOrder.setStatus(newStatus);

        if (!existingOrder.getCustomer().getId().equals(orderRequest.getCustomerId())) {
            Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                    .orElseThrow();
            existingOrder.setCustomer(customer);
        }

        Product oldProduct = existingOrder.getProduct();
        Product newProduct = oldProduct;

        Long newProductId = orderRequest.getProductId();

        if (!oldProduct.getId().equals(newProductId)) {
            newProduct = productRepository.findById(newProductId)
                    .orElseThrow();

            oldProduct.setStock(oldProduct.getStock() + existingOrder.getQuantity());
            productRepository.save(oldProduct);

            if (newProduct.getStock() < orderRequest.getQuantity()) {
                throw new IllegalStateException("Insufficient stock for new product: " + newProduct.getName() +
                        " - stock: " + newProduct.getStock() + " - Quantity ordered: " + orderRequest.getQuantity());
            }

            newProduct.setStock(newProduct.getStock() - orderRequest.getQuantity());
            productRepository.save(newProduct);

            existingOrder.setProduct(newProduct);
        } else {
            int quantityDiff = orderRequest.getQuantity() - existingOrder.getQuantity();
            if (quantityDiff != 0) {
                if (quantityDiff > 0 && newProduct.getStock() < quantityDiff) {
                    throw new IllegalStateException("Not enough stock for product: " + newProduct.getName());
                }
                newProduct.setStock(newProduct.getStock() - quantityDiff);
                productRepository.save(newProduct);
            }
        }
        existingOrder.setQuantity(orderRequest.getQuantity());
        return orderMapper.toResponse(orderRepository.save(existingOrder));
    }

    public OrderResponse cancel(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow();

        if (order.getStatus() == OrderStatus.LIVREE) {
            throw new IllegalStateException("Cannot cancel an order that is already delivered");
        }

        order.setStatus(OrderStatus.ANNULEE);

        order.getProduct().setStock(order.getProduct().getStock() + order.getQuantity());
        productRepository.save(order.getProduct());

        Order cancelled = orderRepository.save(order);

        return orderMapper.toResponse(cancelled);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponse findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();

        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }
}
