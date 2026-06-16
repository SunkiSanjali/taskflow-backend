package com.taskflow.modernjava;

public record Task(
        Long id,
        String title,
        String status
) {
}
