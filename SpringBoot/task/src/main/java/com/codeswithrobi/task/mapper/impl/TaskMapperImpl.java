package com.codeswithrobi.task.mapper.impl;

import org.springframework.stereotype.Component;

import com.codeswithrobi.task.domain.CreateTaskRequest;
import com.codeswithrobi.task.domain.UpdateTaskRequest;
import com.codeswithrobi.task.domain.dto.CreateTaskRequestDto;
import com.codeswithrobi.task.domain.dto.TaskDto;
import com.codeswithrobi.task.domain.dto.UpdateTaskRequestDto;
import com.codeswithrobi.task.domain.entity.Task;
import com.codeswithrobi.task.mapper.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper {

  @Override
  public CreateTaskRequest fromDto(CreateTaskRequestDto dto) {
    return new CreateTaskRequest(
        dto.title(),
        dto.description(),
        dto.dueDate(),
        dto.priority()
        );
  }

  @Override
  public TaskDto toDto(Task task) {
    return new TaskDto(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getDueDate(),
        task.getPriority(),
        task.getStatus()
        );
  }

  @Override
  public UpdateTaskRequest fromDto(UpdateTaskRequestDto dto) {
    return new UpdateTaskRequest(
        dto.title(),
        dto.description(),
        dto.dueDate(),
        dto.status(),
        dto.priority()
        );
  }

}
