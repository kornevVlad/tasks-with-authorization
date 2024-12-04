package com.my.project.dto.comment;

import lombok.Data;

@Data
public class ResponseCommentDto {

    private Long id;
    private String description;
    private Long taskId;
    private Long userId;
}