package supplychainx.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import supplychainx.springboot.common.User.*;
import supplychainx.springboot.common.enums.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserWhenIdExists(){
        User mockUser = new User(1L, "yassir", "mahir", "admin@admin.com", "password", Role.ADMIN);

        UserResponse mockResponse = new UserResponse(1L, "admin@admin.com","yassir", "mahir",  Role.ADMIN);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userMapper.toResponse(mockUser)).thenReturn(mockResponse);

        UserResponse result = userService.getUserById(1L);

        assertEquals("admin@admin.com", result.getEmail());
        assertEquals(Role.ADMIN, result.getRole());
        assertEquals("yassir", result.getFirstName());
        assertEquals("mahir", result.getLastName());

        verify(userRepository).findById(1L);
        verify(userMapper).toResponse(mockUser);
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist(){

    }

}
