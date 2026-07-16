package com.codeswithrobi.task.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codeswithrobi.task.domain.dto.ErrorDto;
import com.codeswithrobi.task.exception.TaskNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String errorMessage = ex.getBindingResult().getFieldErrors().stream()
      .findFirst()
      .map(FieldError::getDefaultMessage).orElse("Validation Failed!");

    ErrorDto errorDto = new ErrorDto(errorMessage);
    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException ex) {
    UUID taskNotFoundId = ex.getId();
    String errorMessage = String.format("Task with ID '%s' not found.", taskNotFoundId);
    ErrorDto errorDto = new ErrorDto(errorMessage);
    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST); //we're not using ex.getMessage() cuz don't want clients to see the internals
  }
}
