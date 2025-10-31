package supplychainx.springboot.production.model;

import jakarta.persistence.*;
import lombok.*;
import supplychainx.springboot.supply.model.RawMaterial;

@Entity
@Table(name = "billOfMaterials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillOfMaterial {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "rawMaterial_id")
    private RawMaterial rawMaterial;
    private int quantity;
}
