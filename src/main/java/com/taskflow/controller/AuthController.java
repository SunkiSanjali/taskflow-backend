package com.taskflow.controller;

import com.taskflow.auth.LoginRequest;
import com.taskflow.auth.RegisterRequest;
import com.taskflow.entity.Role;
import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import com.taskflow.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());

        user.setPassword(passwordEncoder.encode(request.password()));

        // FIX: prevent NULL crash
        String role = request.role();
        if (role == null || role.isBlank()) {
            role = "USER";
        }

        user.setRole(Role.valueOf(role.toUpperCase()));

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElse(null);

        if (user == null) {
            return "Invalid Email";
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            return "Invalid Password";
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return Map.of(
                "token", token,
                "type", "Bearer"
        );
    }
}