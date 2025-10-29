package supplychainx.springboot.supply.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "supplyOrder_rawMaterials")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class SupplyOrderRawMaterial {
    @Id
    private Long id;
    private int quantity;
    @ManyToOne
    private RawMaterial rawMaterial;
    @ManyToOne
    private SupplyOrder supplyOrder;
}
