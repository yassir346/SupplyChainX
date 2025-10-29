package supplychainx.springboot.production.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private Product product;
    @ManyToOne
    private RawMaterial rawMaterial;
    private int quantity;
}
