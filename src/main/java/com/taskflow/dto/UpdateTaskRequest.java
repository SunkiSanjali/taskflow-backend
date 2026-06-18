package com.taskflow.dto;

public record UpdateTaskRequest(
        String title,
        String description,
        String status,
        String priority
) {}