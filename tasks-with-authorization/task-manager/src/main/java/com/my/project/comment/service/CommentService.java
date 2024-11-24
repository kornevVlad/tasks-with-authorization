package com.my.project.comment.service;

import com.my.project.comment.dto.RequestCommentDto;
import com.my.project.comment.dto.ResponseCommentDto;

import java.util.List;


public interface CommentService {

    ResponseCommentDto createComment(String token, RequestCommentDto requestCommentDto, Long taskId);

    ResponseCommentDto  updateComment(String token, RequestCommentDto requestCommentDto, Long commentId);

    List<ResponseCommentDto> getAllCommentsByTaskId(Long taskId, Integer page, Integer size);

    void deleteComment(String token, Long commentId);
}
