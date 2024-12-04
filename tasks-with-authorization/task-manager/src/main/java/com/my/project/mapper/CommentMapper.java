package com.my.project.mapper;

import com.my.project.dto.comment.RequestCommentDto;
import com.my.project.dto.comment.ResponseCommentDto;
import com.my.project.model.Comment;
import com.my.project.model.Task;
import com.my.project.model.User;
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
