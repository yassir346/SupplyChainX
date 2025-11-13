package supplychainx.springboot.common.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import supplychainx.springboot.common.enums.Role;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail().toLowerCase().trim())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (request.getRole() == null) {
            throw new IllegalArgumentException("Role is required");
        }

        User user = userMapper.toEntity(request);
        System.out.println("*************");
        System.out.println(user);
        User saved = userRepository.save(user);
        System.out.println("3333333333");
        return userMapper.toResponse(saved);
    }


    public UserResponse updateUserRole(Long id, Role newRole) {
        if (newRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        user.setRole(newRole);
        User updated = userRepository.save(user);

        return userMapper.toResponse(updated);
    }
}
