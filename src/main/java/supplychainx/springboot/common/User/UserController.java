package supplychainx.springboot.common.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import supplychainx.springboot.common.enums.Role;
import supplychainx.springboot.security.SecuredAction;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @PostMapping
    @SecuredAction(roles = {Role.ADMIN})
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping("/{id}/role")
    @SecuredAction(roles = {Role.ADMIN})
    public ResponseEntity<UserResponse> updateUserRole(
            @PathVariable Long id,
            @RequestParam String newRole
    ) {
        return ResponseEntity.ok(userService.updateUserRole(id, newRole));
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getEmail());
        }else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
