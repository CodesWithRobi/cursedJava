package com.codeswithrobi.task.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.codeswithrobi.task.domain.CreateTaskRequest;
import com.codeswithrobi.task.domain.UpdateTaskRequest;
import com.codeswithrobi.task.domain.entity.Task;
import com.codeswithrobi.task.domain.entity.TaskStatus;
import com.codeswithrobi.task.exception.TaskNotFoundException;
import com.codeswithrobi.task.repository.TaskRepository;
import com.codeswithrobi.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

  private final TaskRepository taskRepository;

  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Task createTask(CreateTaskRequest request) {
    Instant now = Instant.now();
    
    Task task = new Task(
        null,
        request.title(),
        request.description(),
        request.dueDate(),
        TaskStatus.OPEN,
        request.priority(),
        now,
        now);
    
    return taskRepository.save(task);
  }

  @Override
  public List<Task> listTasks() {
    return taskRepository.findAll(Sort.by(Direction.ASC, "created"));
  }

  @Override
  public Task updateTask(UUID id, UpdateTaskRequest request) {

     Task task = taskRepository.findById(id)
       .orElseThrow(() -> new TaskNotFoundException(id));

     task.setTitle(request.title());
     task.setDescription(request.description());
     task.setDueDate(request.dueDate());
     task.setStatus(request.status());
     task.setPriority(request.priority());
     task.setUpdated(Instant.now());

     return taskRepository.save(task);
  }

  @Override
  public void deleteTask(UUID taskId) {
    taskRepository.deleteById(taskId);
  }

}
