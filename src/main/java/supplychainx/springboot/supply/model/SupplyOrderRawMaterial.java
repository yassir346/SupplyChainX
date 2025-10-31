package supplychainx.springboot.supply.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supplyOrder_rawMaterials")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class SupplyOrderRawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "rawMaterial_id")
    private RawMaterial rawMaterial;
    @ManyToOne
    @JoinColumn(name = "supplyOrder_id")
    private SupplyOrder supplyOrder;
}
