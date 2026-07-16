package com.codeswithrobi.task.mapper;

import com.codeswithrobi.task.domain.CreateTaskRequest;
import com.codeswithrobi.task.domain.UpdateTaskRequest;
import com.codeswithrobi.task.domain.dto.CreateTaskRequestDto;
import com.codeswithrobi.task.domain.dto.TaskDto;
import com.codeswithrobi.task.domain.dto.UpdateTaskRequestDto;
import com.codeswithrobi.task.domain.entity.Task;

public interface TaskMapper {

  CreateTaskRequest fromDto(CreateTaskRequestDto dto);
  UpdateTaskRequest fromDto(UpdateTaskRequestDto dto);

  TaskDto toDto(Task task);
}
