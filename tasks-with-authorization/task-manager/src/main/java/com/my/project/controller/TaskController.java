package com.my.project.controller;


import com.my.project.dto.task.NewTaskDto;
import com.my.project.dto.task.TaskDto;
import com.my.project.dto.task.UpdateTaskDto;
import com.my.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @PostMapping()
    public TaskDto createTask(@RequestHeader("Authorization") String token,
                              @RequestBody NewTaskDto newTaskDto) {
        log.info("Создание задачи");
        return taskService.createTask(token, newTaskDto);
    }

    @PatchMapping("/update/{taskId}")
    public TaskDto updateTask(@RequestHeader("Authorization") String token,
                              @RequestBody UpdateTaskDto updateTaskDto,
                              @PathVariable Long taskId) {
        return taskService.updateTask(token, updateTaskDto, taskId);
    }

    @PatchMapping("/update/{executorId}/{taskId}")
    public TaskDto updateTaskExecutorId(@RequestHeader("Authorization") String token,
                                        @PathVariable Long executorId,
                                        @PathVariable Long taskId) {
        return taskService.updateTaskExecutorId(token, executorId, taskId);
    }

    @PatchMapping("/updateStatusTask/{statusTask}/{taskId}")
    public TaskDto updateStatusTask(@RequestHeader("Authorization") String token,
                                        @PathVariable String statusTask,
                                        @PathVariable Long taskId) {
        return taskService.updateStatusTask(token, statusTask, taskId);
    }

    @PatchMapping("/updatePriorityTask/{priority}/{taskId}")
    public TaskDto updatePriorityTask(@RequestHeader("Authorization") String token,
                                    @PathVariable String priority,
                                    @PathVariable Long taskId) {
        return taskService.updatePriorityTask(token, priority, taskId);
    }

    @GetMapping("/getId/{taskId}")
    public TaskDto getTaskId(@PathVariable Long taskId) {
        return taskService.getTaskId(taskId);
    }

    @GetMapping("/allTaskByAuthorId")
    public List<TaskDto> getAllTaskByAuthor(@RequestHeader("Authorization") String token) {
        return taskService.getAllTaskByAuthorId(token);
    }

    @GetMapping("/allTaskByAuthorIdAndStatusPagination")
    public List<TaskDto> getAllTaskByAuthorIdAndStatusPagination(@RequestHeader("Authorization") String token,
                                            @RequestParam(required = false, name = "status") String status,
                                            @RequestParam(required = false, name = "size") Integer size,
                                            @RequestParam(required = false, name = "page") Integer page) {
        return taskService.getAllTaskByAuthorIdAndStatusPagination(token, status, page, size);
    }

    @GetMapping("/allTaskByExecutorId")
    public List<TaskDto> getAllTaskByExecutorId(@RequestHeader("Authorization") String token) {
        return taskService.getAllTaskByExecutorId(token);
    }

    @GetMapping("/allTaskByExecutorIdAndStatusPagination")
    public List<TaskDto> getAllTaskByExecutorIdAndStatusAndPagination(@RequestHeader("Authorization") String token,
                                                @RequestParam(required = false, name = "status") String status,
                                                @RequestParam(required = false, name = "size") Integer size,
                                                @RequestParam(required = false, name = "page") Integer page) {
        return taskService.getAllTaskByExecutorIdAndStatusAndPagination(token, status, page, size);
    }

    @GetMapping("/all")
    public List<TaskDto> getListTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/delete/{taskId}")
    public void deleteTaskById(@RequestHeader("Authorization") String token,
                           @PathVariable Long taskId) {
        taskService.deleteTaskById(token, taskId);
    }
}