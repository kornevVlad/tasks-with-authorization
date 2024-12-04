package com.my.project.dto.task;


import com.my.project.dto.comment.ResponseCommentDto;
import com.my.project.enums.priority.PriorityStatus;
import com.my.project.enums.status.StatusTask;
import lombok.Data;

import java.time.LocalDateTime;
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
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private List<ResponseCommentDto> commentDtoList;
}