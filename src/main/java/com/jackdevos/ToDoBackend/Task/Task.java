package com.jackdevos.ToDoBackend.Task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;
    @Basic
    @Column(name = "taskName", nullable = false, length = 100)
    private String taskName;
    @Column(name = "taskDescription", nullable = false, length = 255)
    private String taskDescription;
    @Column(name = "taskStatus" , nullable = false)
    private boolean taskStatus;
}
