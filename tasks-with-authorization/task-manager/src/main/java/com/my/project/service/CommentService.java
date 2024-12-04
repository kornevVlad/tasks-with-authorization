package com.my.project.service;

import com.my.project.dto.comment.RequestCommentDto;
import com.my.project.dto.comment.ResponseCommentDto;

import java.util.List;


public interface CommentService {

    ResponseCommentDto createComment(String token, RequestCommentDto requestCommentDto, Long taskId);

    ResponseCommentDto  updateComment(String token, RequestCommentDto requestCommentDto, Long commentId);

    List<ResponseCommentDto> getAllCommentsByTaskId(Long taskId, Integer page, Integer size);

    void deleteComment(String token, Long commentId);
}
