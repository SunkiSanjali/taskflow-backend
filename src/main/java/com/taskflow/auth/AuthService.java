package com.taskflow.auth;

import com.taskflow.entity.Role;
import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());

        user.setPassword(
                passwordEncoder.encode(request.password())
        );

        user.setRole(
                Role.valueOf(
                        request.role().toUpperCase()
                )
        );

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(
                request.email()
        ).orElse(null);

        if (user == null) {
            return "Invalid email";
        }

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )) {
            return "Invalid password";
        }

        return "Login successful";
    }
}