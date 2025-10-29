package supplychainx.springboot.production.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import supplychainx.springboot.delivery.model.Order;

import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {
    @Id
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
