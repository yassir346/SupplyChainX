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
    private Customer customer;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Address address;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Delivery delivery;
}
