package supplychainx.springboot.security;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import supplychainx.springboot.common.User.User;
import supplychainx.springboot.common.User.UserRepository;
import supplychainx.springboot.common.enums.Role;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecurityIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        User admin = User.builder()
                .firstName("Admin")
                .lastName("User")
                .email("admin@test.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ADMIN)
                .build();

        User user = User.builder()
                .firstName("Simple")
                .lastName("User")
                .email("user@test.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.GESTIONNAIRE_APPROVISIONNEMENT)
                .build();

        userRepository.save(admin);
        userRepository.save(user);
    }

    @Test
    void shouldLoginAndReturnJwt() throws Exception {
        String body = """
                {
                  "email": "admin@test.com",
                  "password": "password"
                 }
                """;

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());
    }

    // Tester accès SANS token
    @Test
    void shouldRejectAccessWithoutToken() throws Exception {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isForbidden());
    }

    // Tester accès AVEC token
    private String loginAndGetToken(String roleName) throws Exception {

        String email = roleName.equals("admin") ? "admin@test.com" : "user@test.com";

        String body = """
                {
                  "email": "%s",
                  "password": "password"
                }
                """.formatted(email);

        MvcResult result = mockMvc
                .perform(post("/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        return JsonPath.read(response, "$.accessToken");
    }

    // utiliser le token
    @Test
    void shouldAllowAccessWithValidToken() throws Exception {
        String token = loginAndGetToken("user");

        mockMvc.perform(get("/customer/")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }
}
