package com.codeswithrobi.task.domain;

import java.time.LocalDate;

import com.codeswithrobi.task.domain.entity.TaskPriority;

public record CreateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority
) {
}
