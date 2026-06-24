package com.taskflow.auth;

import com.taskflow.entity.Role;
import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUserSuccessfully() {

        RegisterRequest request =
                new RegisterRequest(
                        "Sanjali",
                        "sanjali@test.com",
                        "password123",
                        "MEMBER"
                );

        when(userRepository.existsByEmail(request.email()))
                .thenReturn(false);

        when(passwordEncoder.encode(request.password()))
                .thenReturn("encodedPassword");

        String result = authService.register(request);

        assertEquals(
                "User registered successfully",
                result
        );

        verify(userRepository, times(1))
                .save(any(User.class));
    }

    @Test
    void shouldReturnEmailAlreadyExists() {

        RegisterRequest request =
                new RegisterRequest(
                        "Sanjali",
                        "sanjali@test.com",
                        "password123",
                        "MEMBER"
                );

        when(userRepository.existsByEmail(request.email()))
                .thenReturn(true);

        String result = authService.register(request);

        assertEquals(
                "Email already exists",
                result
        );

        verify(userRepository, never())
                .save(any(User.class));
    }

    @Test
    void shouldLoginSuccessfully() {

        User user = new User();
        user.setEmail("sanjali@test.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.MEMBER);

        LoginRequest request =
                new LoginRequest(
                        "sanjali@test.com",
                        "password123"
                );

        when(userRepository.findByEmail(request.email()))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                request.password(),
                user.getPassword()))
                .thenReturn(true);

        String result = authService.login(request);

        assertEquals(
                "Login successful",
                result
        );
    }

    @Test
    void shouldReturnInvalidEmail() {

        LoginRequest request =
                new LoginRequest(
                        "wrong@test.com",
                        "password123"
                );

        when(userRepository.findByEmail(request.email()))
                .thenReturn(Optional.empty());

        String result = authService.login(request);

        assertEquals(
                "Invalid email",
                result
        );
    }

    @Test
    void shouldReturnInvalidPassword() {

        User user = new User();
        user.setEmail("sanjali@test.com");
        user.setPassword("encodedPassword");

        LoginRequest request =
                new LoginRequest(
                        "sanjali@test.com",
                        "wrongPassword"
                );

        when(userRepository.findByEmail(request.email()))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                request.password(),
                user.getPassword()))
                .thenReturn(false);

        String result = authService.login(request);

        assertEquals(
                "Invalid password",
                result
        );
    }
}