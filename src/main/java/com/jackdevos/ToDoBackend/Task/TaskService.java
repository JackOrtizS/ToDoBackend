package com.jackdevos.ToDoBackend.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskModel taskModel){
        validateTaskModel(taskModel);
        Task task = Task.builder()
                .taskName(taskModel.taskName)
                .taskDescription(taskModel.taskDescription)
                .taskStatus(false)
                .build();
        return taskRepository.save(task);
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Task findTaskById(Long taskId){
        if (taskId == null){
            throw new IllegalArgumentException("Task ID can´t be null");
        }
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    public List<Task> findTaskByStatus(boolean taskStatus){
        return taskRepository.findByTaskStatus(taskStatus);
    }

    public Task updateTask(Long taskId,TaskModel model){
        validateTaskModel(model);

        Task existingTask = findTaskById(taskId);
        existingTask.setTaskName(model.getTaskName());
        existingTask.setTaskDescription(model.getTaskDescription());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId){
        Task task = findTaskById(taskId);
        taskRepository.delete(task);
    }

    public Task updateTaskStatus(Long taskId, boolean taskStatus){
        Task existingTask = findTaskById(taskId);
        existingTask.setTaskStatus(taskStatus);
        return taskRepository.save(existingTask);
    }
    private void validateTaskModel(TaskModel taskModel) {
        if (taskModel== null){
            throw new IllegalArgumentException("Task model can't be null");
        }
        if (taskModel.getTaskName() == null){
            throw new IllegalArgumentException("Task name can't be null or empty");
        }
        if (taskModel.getTaskDescription() == null){
            throw new IllegalArgumentException("Task description can't be null or empty");
        }
    }
}
