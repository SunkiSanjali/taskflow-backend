package com.taskflow.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    void shouldGenerateToken() {

        String token =
                jwtUtil.generateToken(
                        "sanjali@test.com"
                );

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }
}