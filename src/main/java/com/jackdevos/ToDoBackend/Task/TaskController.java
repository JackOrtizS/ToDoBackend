package com.jackdevos.ToDoBackend.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/savetask")
    public ResponseEntity<Task> saveTask(@RequestBody TaskModel request){
        Task savedTask = taskService.createTask(request);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping("/gettasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTask();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/gettaskbyid/{taskId}")
    public ResponseEntity<Task> getTaskByName(@PathVariable Long taskId){
        Task task = taskService.findTaskById(taskId);
        if (task != null){
            return ResponseEntity.ok(task);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestParam boolean taskStatus)
    {
        Task updatedTask = taskService.updateTaskStatus(taskId, taskStatus);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody TaskModel model){
        Task updatedTask = taskService.updateTask(taskId,model);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status/{taskStatus}")
    public ResponseEntity<List<Task>> findTaskByStatus(@PathVariable boolean taskStatus){
        List<Task> tasks = taskService.findTaskByStatus(taskStatus);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
