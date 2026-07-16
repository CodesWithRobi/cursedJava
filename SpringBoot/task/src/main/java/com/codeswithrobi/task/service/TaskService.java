package com.codeswithrobi.task.service;

import java.util.List;
import java.util.UUID;

import com.codeswithrobi.task.domain.CreateTaskRequest;
import com.codeswithrobi.task.domain.UpdateTaskRequest;
import com.codeswithrobi.task.domain.entity.Task;

public interface TaskService {

  Task createTask(CreateTaskRequest request);
  
  List<Task> listTasks();

  Task updateTask(UUID taskId, UpdateTaskRequest request);

  void deleteTask(UUID taskId);
}
