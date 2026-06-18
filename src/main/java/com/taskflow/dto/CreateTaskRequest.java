package com.taskflow.dto;

public record CreateTaskRequest(
        String title,
        String description,
        String priority
) {}