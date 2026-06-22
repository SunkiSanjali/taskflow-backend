package com.taskflow.dto;

public record LoginRequest(
        String email,
        String password
) {}
