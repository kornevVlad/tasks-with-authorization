package com.my.project.model;

import com.my.project.enums.priority.PriorityStatus;
import com.my.project.enums.status.StatusTask;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long Id;

    @Column(name = "task_header")
    private String taskHeader;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id", referencedColumnName = "user_id")
    private User executor;

    @Column(name = "status_task")
    @Enumerated(EnumType.STRING)
    private StatusTask statusTask;

    @Column(name = "priority_status")
    @Enumerated(EnumType.STRING)
    private PriorityStatus priorityStatus;

    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;
}
