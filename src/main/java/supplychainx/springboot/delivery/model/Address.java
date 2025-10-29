package supplychainx.springboot.delivery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    @Id
    private Long id;
    private String city;
    private String zipCode;
    private String street;
}
