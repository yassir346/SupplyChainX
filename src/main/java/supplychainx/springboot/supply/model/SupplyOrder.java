package supplychainx.springboot.supply.model;

import jakarta.persistence.*;
import lombok.*;
import supplychainx.springboot.common.enums.SupplyOrderStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "supplyOrders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class SupplyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    private SupplyOrderStatus status;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @OneToMany(mappedBy = "supplyOrder", cascade = CascadeType.ALL)
    private List<SupplyOrderRawMaterial> supplyOrderRawMaterialList;
}
