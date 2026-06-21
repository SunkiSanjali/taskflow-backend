package com.taskflow.controller;

import com.taskflow.dto.CreateTaskRequest;
import com.taskflow.entity.Task;
import com.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.taskflow.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Page<Task> getTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) Long assigneeId
    ) {

        Pageable pageable =
                PageRequest.of(page, size, Sort.by(sort));

        return taskService.getFilteredTasks(
                status,
                priority,
                assigneeId,
                pageable
        );
    }

    @PostMapping
    public Task createTask(
            @Valid @RequestBody CreateTaskRequest request) {

        return taskService.createTask(request);
    }

    @GetMapping("/test-error")
    public void testError() {
        throw new RuntimeException("Test exception");
    }

    @GetMapping("/test-not-found")
    public void testNotFound() {
        throw new ResourceNotFoundException("Task not found");
    }
}