package com.my.project.comment.utils;

import com.my.project.comment.dto.ResponseCommentDto;
import com.my.project.comment.mapper.CommentMapper;
import com.my.project.comment.model.Comment;
import com.my.project.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentUtils {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public List<ResponseCommentDto> getAllCommentDtoByTaskId(Long taskId) {
        return commentRepository.findAllByTaskId(taskId)
                .stream()
                .map(commentMapper::toResponseCommentDto)
                .collect(Collectors.toList());
    }
}