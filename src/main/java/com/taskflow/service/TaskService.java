package com.taskflow.service;

import com.taskflow.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public Task createTask(Task task) {
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(Long id) {

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }

        return null;
    }

    public Task updateTask(Long id, Task updatedTask) {

        for (Task task : tasks) {

            if (task.getId().equals(id)) {

                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setStatus(updatedTask.getStatus());
                task.setPriority(updatedTask.getPriority());

                return task;
            }
        }

        return null;
    }

    public boolean deleteTask(Long id) {

        for (Task task : tasks) {

            if (task.getId().equals(id)) {
                tasks.remove(task);
                return true;
            }
        }

        return false;
    }
}