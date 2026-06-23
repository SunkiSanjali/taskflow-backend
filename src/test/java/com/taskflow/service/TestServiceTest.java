package com.taskflow.service;

import com.taskflow.dto.CreateTaskRequest;
import com.taskflow.entity.Task;
import com.taskflow.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldCreateTask() {

        CreateTaskRequest request =
                new CreateTaskRequest(
                        "Learn Testing",
                        "JUnit Practice",
                        "HIGH"
                );

        Task savedTask = new Task();
        savedTask.setTitle("Learn Testing");
        savedTask.setDescription("JUnit Practice");
        savedTask.setPriority("HIGH");
        savedTask.setStatus("OPEN");

        when(taskRepository.save(any(Task.class)))
                .thenReturn(savedTask);

        Task result = taskService.createTask(request);

        assertNotNull(result);
        assertEquals("Learn Testing", result.getTitle());
        assertEquals("JUnit Practice", result.getDescription());
        assertEquals("HIGH", result.getPriority());
        assertEquals("OPEN", result.getStatus());

        verify(taskRepository, times(1))
                .save(any(Task.class));
    }

    @Test
    void shouldSetStatusToOpen() {

        CreateTaskRequest request =
                new CreateTaskRequest(
                        "Task",
                        "Description",
                        "MEDIUM"
                );

        Task savedTask = new Task();
        savedTask.setStatus("OPEN");

        when(taskRepository.save(any(Task.class)))
                .thenReturn(savedTask);

        Task result = taskService.createTask(request);

        assertEquals("OPEN", result.getStatus());
    }

    @Test
    void shouldCallRepositorySaveOnce() {

        CreateTaskRequest request =
                new CreateTaskRequest(
                        "Task",
                        "Description",
                        "LOW"
                );

        when(taskRepository.save(any(Task.class)))
                .thenReturn(new Task());

        taskService.createTask(request);

        verify(taskRepository, times(1))
                .save(any(Task.class));
    }
    @Test
    void shouldGetTaskById() {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Testing");

        when(taskRepository.findById(1L))
                .thenReturn(java.util.Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertEquals("Testing", result.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {

        when(taskRepository.findById(99L))
                .thenReturn(java.util.Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> taskService.getTaskById(99L)
                );

        assertEquals(
                "Task not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldUpdateTask() {

        Task task = new Task();

        task.setId(1L);
        task.setTitle("Old Title");

        CreateTaskRequest request =
                new CreateTaskRequest(
                        "New Title",
                        "New Description",
                        "HIGH"
                );

        when(taskRepository.findById(1L))
                .thenReturn(java.util.Optional.of(task));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        Task result =
                taskService.updateTask(1L, request);

        assertEquals(
                "New Title",
                result.getTitle()
        );
    }

    @Test
    void shouldDeleteTask() {

        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L))
                .thenReturn(java.util.Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository)
                .delete(task);
    }
    @Test
    void shouldGetAllTasks() {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Task> page =
                new PageImpl<>(List.of(new Task()));

        when(taskRepository.findAll(pageable))
                .thenReturn(page);

        Page<Task> result =
                taskService.getAllTasks(pageable);

        assertEquals(1, result.getContent().size());

        verify(taskRepository)
                .findAll(pageable);
    }
    @Test
    void shouldGetFilteredTasks() {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Task> page =
                new PageImpl<>(List.of(new Task()));

        when(taskRepository.findAll(
                any(org.springframework.data.jpa.domain.Specification.class),
                eq(pageable)))
                .thenReturn(page);

        Page<Task> result =
                taskService.getFilteredTasks(
                        "OPEN",
                        "HIGH",
                        null,
                        pageable
                );

        assertEquals(1, result.getContent().size());
    }
    @Test
    void shouldFilterByStatusOnly() {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Task> page =
                new PageImpl<>(List.of(new Task()));

        when(taskRepository.findAll(
                any(org.springframework.data.jpa.domain.Specification.class),
                eq(pageable)))
                .thenReturn(page);

        Page<Task> result =
                taskService.getFilteredTasks(
                        "OPEN",
                        null,
                        null,
                        pageable
                );

        assertEquals(1, result.getContent().size());
    }
    @Test
    void shouldFilterByPriorityOnly() {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Task> page =
                new PageImpl<>(List.of(new Task()));

        when(taskRepository.findAll(
                any(org.springframework.data.jpa.domain.Specification.class),
                eq(pageable)))
                .thenReturn(page);

        Page<Task> result =
                taskService.getFilteredTasks(
                        null,
                        "HIGH",
                        null,
                        pageable
                );

        assertEquals(1, result.getContent().size());
    }
    @Test
    void shouldFilterByAssigneeOnly() {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Task> page =
                new PageImpl<>(List.of(new Task()));

        when(taskRepository.findAll(
                any(org.springframework.data.jpa.domain.Specification.class),
                eq(pageable)))
                .thenReturn(page);

        Page<Task> result =
                taskService.getFilteredTasks(
                        null,
                        null,
                        1L,
                        pageable
                );

        assertEquals(1, result.getContent().size());
    }
}