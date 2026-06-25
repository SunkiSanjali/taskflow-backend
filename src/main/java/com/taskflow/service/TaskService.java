package com.taskflow.service;

import com.taskflow.dto.CreateTaskRequest;
import com.taskflow.entity.Task;
import com.taskflow.repository.TaskRepository;
import com.taskflow.specification.TaskSpecification;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Page<Task> getFilteredTasks(
            String status,
            String priority,
            Long assigneeId,
            Pageable pageable) {

        Specification<Task> spec =
                Specification.where(TaskSpecification.hasStatus(status))
                        .and(TaskSpecification.hasPriority(priority))
                        .and(TaskSpecification.hasAssignee(assigneeId));

        return taskRepository.findAll(spec, pageable);
    }

    public Task createTask(CreateTaskRequest request) {

        Task task = new Task();

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setPriority(request.priority());
        task.setStatus("OPEN");

        return taskRepository.save(task);
    }

    @Cacheable(value = "tasks", key = "#id")
    public Task getTaskById(Long id) {

        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found"));
    }

    @CachePut(value = "tasks", key = "#id")
    public Task updateTask(Long id, CreateTaskRequest request) {

        Task task = getTaskById(id);

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setPriority(request.priority());

        return taskRepository.save(task);
    }

    @CacheEvict(value = "tasks", key = "#id")
    public void deleteTask(Long id) {

        Task task = getTaskById(id);

        taskRepository.delete(task);
    }
}