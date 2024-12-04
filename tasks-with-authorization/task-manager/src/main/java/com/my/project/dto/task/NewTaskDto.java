package com.my.project.dto.task;

import com.my.project.enums.priority.PriorityStatus;
import lombok.Data;

@Data
public class NewTaskDto {
    private String taskHeader;
    private String description;
    private PriorityStatus priorityStatus;
}
