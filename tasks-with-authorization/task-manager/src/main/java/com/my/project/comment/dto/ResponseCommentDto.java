package com.my.project.comment.dto;

import lombok.Data;

@Data
public class ResponseCommentDto {

    private Long id;
    private String description;
    private Long taskId;
    private Long userId;
}