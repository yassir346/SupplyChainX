package supplychainx.springboot.supply.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rawMaterials")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class RawMaterial {
    @Id
    private Long id;
    private String name;
    private int stock;
    private int stockMin;
    private String unit;
    @OneToMany(mappedBy = "rawMaterial")
    private List<SupplyOrderRawMaterial> supplyOrderRawMaterials;
    @ManyToMany
    private List<Supplier> suppliers;
}
