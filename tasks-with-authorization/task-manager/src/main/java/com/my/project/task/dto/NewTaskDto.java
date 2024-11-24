package com.my.project.task.dto;

import com.my.project.task.priority.PriorityStatus;
import lombok.Data;

@Data
public class NewTaskDto {
    private String taskHeader;
    private String description;
    private PriorityStatus priorityStatus;
}
