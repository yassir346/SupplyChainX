package supplychainx.springboot.production.model;

import jakarta.persistence.*;
import lombok.*;
import supplychainx.springboot.common.enums.ProductionOrderStatus;

import java.time.LocalDate;

@Entity
@Table(name = "productionOrders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductionOrder {
    @Id
    private Long id;
    @ManyToOne
    private Product product;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ProductionOrderStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
}
