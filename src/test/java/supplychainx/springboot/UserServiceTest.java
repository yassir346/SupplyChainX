package supplychainx.springboot;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import supplychainx.springboot.common.User.*;
import supplychainx.springboot.common.enums.Role;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        when(userRepository.findById(44L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.getUserById(44L));

        assertEquals("User not found with id: 44", exception.getMessage());
    }

    @Test
    void ShouldCreateUserSuccessfully(){
        UserRequest request = new UserRequest("admin@admin.com", "password", "yassir", "mahir", Role.ADMIN);

        User entity = new User(0,    "yassir", "mahir", "admin@admin.com", "password", Role.ADMIN);

        User saved = new User(1L, "yassir", "mahir", "admin@admin.com", "password", Role.ADMIN);

        UserResponse response = new UserResponse(1L, "admin@admin.com","yassir", "mahir",  Role.ADMIN);

        when(userRepository.existsByEmail("admin@admin.com")).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(saved);
        when(userMapper.toResponse(saved)).thenReturn(response);

        UserResponse result = userService.createUser(request);

        assertEquals("admin@admin.com", result.getEmail());
        assertEquals("yassir", result.getFirstName());
        assertEquals("mahir", result.getLastName());
        assertEquals(Role.ADMIN, result.getRole());

        verify(userRepository).existsByEmail("admin@admin.com");
        verify(userMapper).toEntity(request);
        verify(userRepository).save(entity);
        verify(userMapper).toResponse(saved);
    }

    @Test
    void souldThrowExceptionWhenEmailAlreadyExists(){
        UserRequest request = new UserRequest("admin@admin.com", "password", "yassir", "mahir", Role.ADMIN);

        when(userRepository.existsByEmail("admin@admin.com")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(request));

        assertEquals("Email already exists", exception.getMessage());
    }


}
