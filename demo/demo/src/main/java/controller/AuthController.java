package controller;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setRoles(Set.of("ROLE_USER"));  // For regular users
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody User user) {
        // Set roles for admin users
        user.setRoles(Set.of("ROLE_ADMIN", "ROLE_USER"));
        User savedAdmin = userService.saveUser(user);
        return ResponseEntity.ok(savedAdmin);
    }
}