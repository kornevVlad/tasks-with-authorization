package com.my.project.task.mapper;

import com.my.project.comment.dto.ResponseCommentDto;
import com.my.project.task.dto.NewTaskDto;
import com.my.project.task.dto.TaskDto;
import com.my.project.task.model.Task;
import com.my.project.task.status.StatusTask;
import com.my.project.user.model.User;
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