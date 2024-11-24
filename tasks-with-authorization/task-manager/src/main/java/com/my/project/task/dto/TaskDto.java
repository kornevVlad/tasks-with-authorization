package com.my.project.task.dto;


import com.my.project.comment.dto.ResponseCommentDto;
import com.my.project.task.priority.PriorityStatus;
import com.my.project.task.status.StatusTask;
import lombok.Data;

import java.util.List;


@Data
public class TaskDto {
    private Long commentId;
    private String taskHeader;
    private String description;
    private Long author;
    private Long Executor;
    private StatusTask statusTask;
    private PriorityStatus priorityStatus;
    private List<ResponseCommentDto> commentDtoList;
}