package com.my.project.mapper;

import com.my.project.dto.comment.ResponseCommentDto;
import com.my.project.dto.task.NewTaskDto;
import com.my.project.dto.task.TaskDto;
import com.my.project.model.Task;
import com.my.project.enums.status.StatusTask;
import com.my.project.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    public Task toNewTask(NewTaskDto newTaskDto, User user) {
        Task task = new Task();
        task.setTaskHeader(newTaskDto.getTaskHeader());
        task.setDescription(newTaskDto.getDescription());
        task.setAuthor(user);
        task.setStatusTask(StatusTask.PENDING);
        task.setPriorityStatus(newTaskDto.getPriorityStatus());
        return task;
    }

    public TaskDto toTaskDto(Task task, List<ResponseCommentDto> commentDtoList) {
        TaskDto taskDto = new TaskDto();
        taskDto.setCommentId(task.getId());
        taskDto.setTaskHeader(task.getTaskHeader());
        taskDto.setDescription(task.getDescription());
        taskDto.setAuthor(task.getAuthor().getId());
        taskDto.setStatusTask(task.getStatusTask());
        if (task.getExecutor()!=null) {
            taskDto.setExecutor(task.getExecutor().getId());
        }
        taskDto.setPriorityStatus(task.getPriorityStatus());
        taskDto.setCommentDtoList(commentDtoList);
        return taskDto;
    }
}