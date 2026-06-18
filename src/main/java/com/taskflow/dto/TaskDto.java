package com.taskflow.dto;

public record TaskDto(
        Long id,
        String title,
        String description,
        String status,
        String priority
) {}