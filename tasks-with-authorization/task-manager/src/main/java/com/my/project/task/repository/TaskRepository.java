package com.my.project.task.repository;

import com.my.project.task.model.Task;
import com.my.project.task.status.StatusTask;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByAuthorId(Long authorId);

    List<Task> findAllByExecutorId(Long executorId);

    List<Task> findAllByAuthorIdAndStatusTask(Long authorId, StatusTask status, PageRequest pageRequest);

    List<Task> findAllByExecutorIdAndStatusTask(Long authorId, StatusTask status, PageRequest pageRequest);
}