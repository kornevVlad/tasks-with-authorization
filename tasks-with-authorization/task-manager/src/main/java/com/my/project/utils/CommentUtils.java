package com.my.project.utils;

import com.my.project.dto.comment.ResponseCommentDto;
import com.my.project.mapper.CommentMapper;
import com.my.project.repository.CommentRepository;
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