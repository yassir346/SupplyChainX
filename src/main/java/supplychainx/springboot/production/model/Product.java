package supplychainx.springboot.production.model;

import jakarta.persistence.*;
import lombok.*;
import supplychainx.springboot.delivery.model.Order;

import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int productionTime;
    private double cost;
    private int stock;
    @OneToMany(mappedBy = "product")
    private List<BillOfMaterial> billOfMaterials;
    @OneToMany(mappedBy = "product")
    private List<ProductionOrder> productionOrders;
    @OneToMany(mappedBy = "product")
    private List<Order> orders;
}
