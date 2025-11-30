package supplychainx.springboot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import supplychainx.springboot.common.User.User;
import supplychainx.springboot.common.User.UserRepository;
import supplychainx.springboot.common.enums.Role;
import supplychainx.springboot.supply.dto.SupplyRequestDTO;
import supplychainx.springboot.supply.model.Supplier;
import supplychainx.springboot.supply.repository.ISupplierRepository;
import supplychainx.springboot.supply.service.ISupplierService;
import supplychainx.springboot.supply.service.Impl.SupplierServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class SupplierControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ISupplierRepository supplierRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private UserRepository userRepository;
    @Autowired
    private SupplierServiceImpl supplierService;
    @BeforeEach
    void setup(){
        supplierRepository.deleteAll();
    }

    @Test
    void createSupplier_shouldReturn200() throws Exception{
        final String TEST_EMAIL = "test@supplychainx.com";
        final String TEST_PASSWORD = "testpassword";

        User authorizedUser = new User();
        authorizedUser.setEmail(TEST_EMAIL);
        authorizedUser.setPassword(TEST_PASSWORD);
        authorizedUser.setRole(Role.GESTIONNAIRE_APPROVISIONNEMENT);

        when(userRepository.findByEmailAndPassword(TEST_EMAIL, TEST_PASSWORD))
                .thenReturn(Optional.of(authorizedUser));

        SupplyRequestDTO supplier = new SupplyRequestDTO();
        supplier.setName("name");
        supplier.setContact("contact@example.com");
        supplier.setRating(0);
        supplier.setRawMaterialList(List.of());

        mockMvc.perform(post("/supplier/add")
                        .header("X-User-Email", TEST_EMAIL)
                        .header("X-User-Password", TEST_PASSWORD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name").value("name"));
    }

    @Test
    void getSupplierById_shouldReturn200() throws Exception{

        Supplier supplier = new Supplier();
        supplier.setName("name");
        supplierRepository.save(supplier);

        mockMvc.perform(get("/supplier/" + supplier.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"));
    }
}
