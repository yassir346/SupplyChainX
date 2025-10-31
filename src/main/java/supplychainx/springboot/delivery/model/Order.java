package supplychainx.springboot.delivery.model;

import jakarta.persistence.*;
import lombok.*;
import supplychainx.springboot.common.enums.OrderStatus;
import supplychainx.springboot.production.model.Product;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    private Long id;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Delivery delivery;
}
