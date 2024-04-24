package com.jackdevos.ToDoBackend.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTaskName(String taskName);

    List<Task> findByTaskStatus(boolean taskStatus);
}
