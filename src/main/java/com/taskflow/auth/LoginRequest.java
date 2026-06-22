package com.taskflow.auth;

public record LoginRequest(
        String email,
        String password
) {
}