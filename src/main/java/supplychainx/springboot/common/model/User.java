package supplychainx.springboot.common.model;

import jakarta.persistence.*;
import lombok.*;
import supplychainx.springboot.common.enums.Role;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
