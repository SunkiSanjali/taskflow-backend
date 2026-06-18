package com.taskflow.mapper;

import com.taskflow.dto.TaskDto;
import com.taskflow.entity.Task;

public class TaskMapper {

    public static TaskDto toDto(Task task) {

        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority()
        );
    }
}