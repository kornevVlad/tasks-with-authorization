package com.my.project.comment.repository;

import com.my.project.comment.model.Comment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTaskId(PageRequest pageRequest, Long taskId);

    List<Comment> findAllByTaskId(Long taskId);
}
