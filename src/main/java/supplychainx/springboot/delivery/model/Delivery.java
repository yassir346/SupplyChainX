package supplychainx.springboot.delivery.model;

import jakarta.persistence.*;
import lombok.*;
import supplychainx.springboot.common.enums.DeliveryStatus;

import java.time.LocalDate;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Delivery {
    @Id
    private Long id;
    @OneToOne
    private Order order;
    private String vehicle;
    private String driver;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    private LocalDate deliveryDate;
    private double cost;
}
