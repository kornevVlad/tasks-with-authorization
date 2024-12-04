package com.my.project.service;

import com.my.project.dto.task.NewTaskDto;
import com.my.project.dto.task.TaskDto;
import com.my.project.dto.task.UpdateTaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createTask(String token, NewTaskDto newTaskDto);

    TaskDto updateTask(String token, UpdateTaskDto updateTaskDto, Long taskId);

    TaskDto updateTaskExecutorId(String token, Long executorId, Long taskId);

    TaskDto updateStatusTask(String token, String statusTask, Long taskId);

    TaskDto updatePriorityTask(String token, String priority, Long taskId);

    TaskDto getTaskId(Long taskId);

    List<TaskDto> getAllTaskByAuthorId(String token);

    List<TaskDto> getAllTaskByAuthorIdAndStatusPagination(String token, String status, Integer page, Integer size);

    List<TaskDto> getAllTaskByExecutorId(String token);

    List<TaskDto> getAllTaskByExecutorIdAndStatusAndPagination(String token, String status, Integer page, Integer size);

    List<TaskDto> getAllTasks();

    void deleteTaskById(String token, Long taskId);
}
