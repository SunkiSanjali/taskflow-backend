package com.taskflow.controller;

import com.taskflow.dto.CreateTaskRequest;
import com.taskflow.entity.Task;
import com.taskflow.exception.ResourceNotFoundException;
import com.taskflow.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task Management", description = "APIs for managing tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Get all tasks",
            description = "Returns paginated and filtered tasks"
    )
    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
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

    @Operation(
            summary = "Create a task",
            description = "Creates a new task in the system"
    )
    @ApiResponse(responseCode = "200", description = "Task created successfully")
    @PostMapping
    public Task createTask(
            @Valid @RequestBody CreateTaskRequest request) {

        return taskService.createTask(request);
    }

    @Operation(summary = "Generate test exception")
    @GetMapping("/test-error")
    public void testError() {
        throw new RuntimeException("Test exception");
    }

    @Operation(summary = "Generate resource not found exception")
    @GetMapping("/test-not-found")
    public void testNotFound() {
        throw new ResourceNotFoundException("Task not found");
    }

    @Operation(
            summary = "Protected endpoint",
            description = "Accessible only to authenticated users"
    )
    @ApiResponse(responseCode = "200", description = "Access granted")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/secure")
    public String secureEndpoint() {
        return "Protected Endpoint";
    }
    @Operation(summary = "Get task by ID")
    @ApiResponse(responseCode = "200", description = "Task found")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    @Operation(
            summary = "Update a task",
            description = "Updates an existing task"
    )
    @ApiResponse(responseCode = "200", description = "Task updated successfully")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @Valid @RequestBody CreateTaskRequest request) {

        return taskService.updateTask(id, request);
    }

    @Operation(
            summary = "Delete a task",
            description = "Deletes a task by ID"
    )
    @ApiResponse(responseCode = "200", description = "Task deleted successfully")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);
    }
}