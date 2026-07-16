package com.codeswithrobi.task.domain;

import java.time.LocalDate;

import com.codeswithrobi.task.domain.entity.TaskPriority;
import com.codeswithrobi.task.domain.entity.TaskStatus;

public record UpdateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskStatus status,
    TaskPriority priority
    ) {
}
