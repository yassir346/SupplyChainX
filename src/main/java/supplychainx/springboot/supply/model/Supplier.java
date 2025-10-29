package supplychainx.springboot.supply.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Supplier {
    @Id
    private Long id;
    private String name;
    @Column(unique = true)
    private String contact;
    private double rating;
    @OneToMany(mappedBy = "supplier")
    private List<SupplyOrder> supplyOrders;
    @ManyToMany
    @JoinTable(
            name = "suppliers_rawMaterials",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "rawMateial_id")
    )
    private List<RawMaterial> rawMaterials;
}
