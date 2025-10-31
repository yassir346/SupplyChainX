package supplychainx.springboot.supply.model;

import jakarta.persistence.*;
import lombok.*;
import supplychainx.springboot.production.model.BillOfMaterial;

import java.util.List;

@Entity
@Table(name = "rawMaterials")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    private int stockMin;
    private String unit;
    @OneToMany(mappedBy = "rawMaterial")
    private List<SupplyOrderRawMaterial> supplyOrderRawMaterials;
    @OneToMany(mappedBy = "rawMaterial")
    private List<BillOfMaterial> billOfMaterialList;
}
