package com.my.project.controller;

import com.my.project.dto.comment.RequestCommentDto;
import com.my.project.dto.comment.ResponseCommentDto;
import com.my.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{taskId}")
    public ResponseCommentDto createComment(@RequestHeader("Authorization") String token,
                                            @RequestBody RequestCommentDto requestCommentDto,
                                            @PathVariable Long taskId) {
        return commentService.createComment(token, requestCommentDto, taskId);
    }

    @PatchMapping("/update/{commentId}")
    public ResponseCommentDto updateComment(@RequestHeader("Authorization") String token,
                                           @RequestBody RequestCommentDto requestCommentDto,
                                           @PathVariable Long commentId) {
        return commentService.updateComment(token, requestCommentDto, commentId);
    }

    @GetMapping("/getCommentsByTaskId/{taskId}")
    public List<ResponseCommentDto> getAllCommentsByTaskId(@PathVariable Long taskId,
                                                           @RequestParam(required = false) Integer page,
                                                           @RequestParam(required = false) Integer size) {
        return commentService.getAllCommentsByTaskId(taskId, page, size);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@RequestHeader("Authorization") String token,
                              @PathVariable Long commentId) {
        commentService.deleteComment(token, commentId);
    }
}
