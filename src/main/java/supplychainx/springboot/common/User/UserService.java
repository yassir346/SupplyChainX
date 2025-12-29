package supplychainx.springboot.common.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import supplychainx.springboot.common.enums.Role;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail().toLowerCase().trim())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (request.getRole() == null) {
            throw new IllegalArgumentException("Role is required");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(encoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }


    public UserResponse updateUserRole(Long id, String newRole) {
        if (newRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        user.setRole(Role.valueOf(newRole));
        User updated = userRepository.save(user);

        return userMapper.toResponse(updated);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        return userMapper.toResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if(username.isEmpty()){
            throw new UsernameNotFoundException("User not found with email" + username);
        }
        return new UserInfoDetails(user.get());
    }
}
