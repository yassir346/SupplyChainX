package supplychainx.springboot.common.User;

import lombok.*;
import supplychainx.springboot.common.enums.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
