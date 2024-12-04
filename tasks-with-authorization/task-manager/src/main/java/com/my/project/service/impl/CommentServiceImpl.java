package com.my.project.service.impl;

import com.my.project.utils.JwtTokenUtils;
import com.my.project.dto.comment.RequestCommentDto;
import com.my.project.dto.comment.ResponseCommentDto;
import com.my.project.mapper.CommentMapper;
import com.my.project.model.Comment;
import com.my.project.repository.CommentRepository;
import com.my.project.service.CommentService;
import com.my.project.exception.ValidBedRequest;
import com.my.project.exception.ValidNotFound;
import com.my.project.model.Task;
import com.my.project.repository.TaskRepository;
import com.my.project.model.User;
import com.my.project.repository.UserRepository;
import com.my.project.enums.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    @Override
    public ResponseCommentDto createComment(String token, RequestCommentDto requestCommentDto, Long taskId) {
        User user = validUser(token);
        Task task = validTask(taskId);
        Comment comment = new Comment();
        if (user.getId() == task.getAuthor().getId()) {
            comment = commentMapper.toComment(requestCommentDto, user, task);
        } else if (user.getRole().equals(Role.ADMIN)) {
            comment = commentMapper.toComment(requestCommentDto, user, task);
        } else if (user.getId() == task.getExecutor().getId()) {
            comment = commentMapper.toComment(requestCommentDto, user, task);
        }
            commentRepository.save(comment);

        return commentMapper.toResponseCommentDto(comment);
    }

    @Override
    public ResponseCommentDto updateComment(String token, RequestCommentDto requestCommentDto, Long commentId) {
        User user = validUser(token);
        Comment comment = validComment(commentId);
        if (comment.getUser().getId() == user.getId() | user.getRole().equals(Role.ADMIN)) {
            comment.setDescription(requestCommentDto.getDescription());
            commentRepository.save(comment);
        }
        return commentMapper.toResponseCommentDto(comment);
    }

    @Override
    public List<ResponseCommentDto> getAllCommentsByTaskId(Long taskId, Integer page, Integer size) {
        Task task = validTask(taskId);
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Comment> comments = commentRepository.findAllByTaskId(pageRequest, taskId);
        List<ResponseCommentDto> commentsDto = new ArrayList<>();
        for (Comment comment : comments) {
            commentsDto.add(commentMapper.toResponseCommentDto(comment));
        }
        return commentsDto;
    }

    @Override
    public void deleteComment(String token, Long commentId) {
        User user = validUser(token);
        Comment comment = validComment(commentId);
        if (comment.getUser().getId() == user.getId() | user.getRole().equals(Role.ADMIN)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new ValidBedRequest("Нет прав доступа для удаления комментария");
        }
    }

    private User validUser(String token) {
        String email = jwtTokenUtils.getEmail(token);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new ValidNotFound("Пользователь не найден");
        }
        return user.get();
    }

    private Task validTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            throw new ValidNotFound("Задача не найдена");
        }
        return task.get();
    }

    private Comment validComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            throw new ValidNotFound("Комментарий не найден");
        }
        return comment.get();
    }
}