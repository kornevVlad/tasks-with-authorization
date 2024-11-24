package com.my.project.comment.mapper;

import com.my.project.comment.dto.RequestCommentDto;
import com.my.project.comment.dto.ResponseCommentDto;
import com.my.project.comment.model.Comment;
import com.my.project.task.model.Task;
import com.my.project.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public ResponseCommentDto toResponseCommentDto(Comment comment) {
        ResponseCommentDto responseCommentDto = new ResponseCommentDto();
        responseCommentDto.setId(comment.getId());
        responseCommentDto.setDescription(comment.getDescription());
        responseCommentDto.setTaskId(comment.getTask().getId());
        responseCommentDto.setUserId(comment.getUser().getId());
        return responseCommentDto;
    }

    public Comment toComment(RequestCommentDto requestCommentDto, User user, Task task) {
        Comment comment = new Comment();
        comment.setDescription(requestCommentDto.getDescription());
        comment.setUser(user);
        comment.setTask(task);
        return comment;
    }
}
