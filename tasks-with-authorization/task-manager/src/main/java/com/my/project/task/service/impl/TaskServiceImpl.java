package com.my.project.task.service.impl;

import com.my.project.authentication.utils.JwtTokenUtils;
import com.my.project.comment.utils.CommentUtils;
import com.my.project.exception.ValidBedRequest;
import com.my.project.exception.ValidNotFound;
import com.my.project.task.dto.NewTaskDto;
import com.my.project.task.dto.TaskDto;
import com.my.project.task.dto.UpdateTaskDto;
import com.my.project.task.mapper.TaskMapper;
import com.my.project.task.model.Task;
import com.my.project.task.priority.PriorityStatus;
import com.my.project.task.repository.TaskRepository;
import com.my.project.task.service.TaskService;
import com.my.project.task.status.StatusTask;
import com.my.project.user.model.User;
import com.my.project.user.repository.UserRepository;
import com.my.project.user.type_access.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final TaskMapper taskMapper;
    private final CommentUtils commentUtils;

    @Override
    public TaskDto createTask(String token, NewTaskDto newTaskDto) {
        Task task = taskMapper.toNewTask(newTaskDto, validUser(token));
        taskRepository.save(task);
        return taskMapper.toTaskDto(task, commentUtils.getAllCommentDtoByTaskId(task.getId()));
    }

    @Override
    public TaskDto updateTask(String token, UpdateTaskDto updateTaskDto, Long taskId) {
        User user = validUser(token);
        Task task = validTask(taskId);
        if (task.getAuthor().getId() == user.getId() | user.getRole().equals(Role.ADMIN)) {
            if (updateTaskDto.getTaskHeader() != null) {
                task.setTaskHeader(updateTaskDto.getTaskHeader());
            }
            if (updateTaskDto.getDescription() != null) {
                task.setDescription(updateTaskDto.getDescription());
            }
            taskRepository.save(task);
        }
        return taskMapper.toTaskDto(task, commentUtils.getAllCommentDtoByTaskId(task.getId()));
    }

    @Override
    public TaskDto updateTaskExecutorId(String token, Long executorId, Long taskId) {
        User user = validUser(token);
        Task task = validTask(taskId);
        User executor = validUserExecutor(executorId);
        if (user.getId() == task.getAuthor().getId() | user.getRole().equals(Role.ADMIN)) {
            if (task.getExecutor() == null) {
                task.setExecutor(executor);
                task.setStatusTask(StatusTask.IN_PROGRESS);
                taskRepository.save(task);
            }
        }
        return taskMapper.toTaskDto(task, commentUtils.getAllCommentDtoByTaskId(task.getId()));
    }

    @Override
    public TaskDto updateStatusTask(String token, String statusTask, Long taskId) {
        User user = validUser(token);
        Task task = validTask(taskId);
        if (user.getId() == task.getExecutor().getId() | user.getRole().equals(Role.ADMIN)) {
            task.setStatusTask(StatusTask.valueOf(statusTask));
            taskRepository.save(task);
        }
        return taskMapper.toTaskDto(task, commentUtils.getAllCommentDtoByTaskId(task.getId()));
    }

    @Override
    public TaskDto updatePriorityTask(String token, String priority, Long taskId) {
        User user = validUser(token);
        Task task = validTask(taskId);
        if (user.getId() == task.getAuthor().getId() | user.getRole().equals(Role.ADMIN)) {
            task.setPriorityStatus(PriorityStatus.valueOf(priority));
            taskRepository.save(task);
        }
        return taskMapper.toTaskDto(task, commentUtils.getAllCommentDtoByTaskId(task.getId()));
    }

    @Override
    public TaskDto getTaskId(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            throw new ValidNotFound("Задача не найдена");
        }
        return taskMapper.toTaskDto(task.get(), commentUtils.getAllCommentDtoByTaskId(task.get().getId()));
    }

    @Override
    public List<TaskDto> getAllTaskByAuthorId(String token) {
        User user = validUser(token);
        return generateListTaskDto(taskRepository.findAllByAuthorId(user.getId()));
    }

    @Override
    public List<TaskDto> getAllTaskByAuthorIdAndStatusPagination(String token,
                                                                 String status,
                                                                 Integer page,
                                                                 Integer size) {
        User user = validUser(token);
        return generateListTaskDto(taskRepository.
                findAllByAuthorIdAndStatusTask(user.getId(), StatusTask.valueOf(status), PageRequest.of(page, size)));
    }

    @Override
    public List<TaskDto> getAllTaskByExecutorId(String token) {
        User user = validUser(token);
        return generateListTaskDto(taskRepository.findAllByExecutorId(user.getId()));
    }

    @Override
    public List<TaskDto> getAllTaskByExecutorIdAndStatusAndPagination(String token,
                                                                      String status,
                                                                      Integer page,
                                                                      Integer size) {
        User user = validUser(token);
        return generateListTaskDto(taskRepository.
                findAllByExecutorIdAndStatusTask(user.getId(), StatusTask.valueOf(status), PageRequest.of(page, size)));
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return generateListTaskDto(taskRepository.findAll());
    }

    @Override
    public void deleteTaskById(String token, Long taskId) {
        User user = validUser(token);
        Task task = validTask(taskId);
        if (task.getAuthor().getId() == user.getId()) {
            taskRepository.deleteById(taskId);
        } else if (user.getRole().equals(Role.ADMIN)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new ValidBedRequest("Нет прав доступа для удаления задачи");
        }
    }

    private List<TaskDto> generateListTaskDto(List<Task> tasks) {
        List<TaskDto> tasksDto = new ArrayList<>();
        for (Task task : tasks) {
            tasksDto.add(taskMapper.toTaskDto(task, commentUtils.getAllCommentDtoByTaskId(task.getId())));
        }
        return tasksDto;
    }

    private Task validTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            throw new ValidNotFound("Задача не найдена");
        }
        return task.get();
    }
    private User validUser(String token) {
        String email = jwtTokenUtils.getEmail(token);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new ValidNotFound("Пользователь не найден");
        }
        return user.get();
    }

    private User validUserExecutor(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ValidNotFound("Пользователь не найден");
        }
        return user.get();
    }
}