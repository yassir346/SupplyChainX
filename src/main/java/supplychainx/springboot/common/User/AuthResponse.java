package supplychainx.springboot.common.User;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;

//    private String refreshToken;

    private long expiresIn;
}
