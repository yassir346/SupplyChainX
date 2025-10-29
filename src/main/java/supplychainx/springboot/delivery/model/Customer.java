package supplychainx.springboot.delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    private Long id;
    private String name;
    private String address;
    private String City;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;


}
