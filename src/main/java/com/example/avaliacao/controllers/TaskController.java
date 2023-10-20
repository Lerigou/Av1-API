package com.example.avaliacao.controllers;

import com.example.avaliacao.entities.TaskEntity;
import com.example.avaliacao.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/av/task")
public class TaskController {

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("add")
    public ResponseEntity<?> newTask(@RequestBody TaskEntity task){
        try{
            task = taskService.newTask(task);
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity("New task creation failed",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all")
    public ResponseEntity<?> searchTasks(){
        try {
            List<TaskEntity> tasksList = taskService.searchTasks();
            return new ResponseEntity(tasksList, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    (ex.getMessage(),
                            HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchTask(@PathVariable("id") Long task_id){
        try {
            TaskEntity task = taskService.searchTask(task_id);
            return new ResponseEntity(task, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    (ex.getMessage(),
                            HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long task_id){
        try {
            taskService.deleteTask(task_id);
            return new ResponseEntity<>("Successful deletion!", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public TaskEntity editTask(@PathVariable("id") Long task_id, @RequestBody TaskEntity updateTask) throws Exception {
        return taskService.editTask(task_id, updateTask);
    }

    @PatchMapping("/{id}")
    public TaskEntity editStatus(@PathVariable("id") Long task_id, @RequestBody TaskEntity updateStatus) throws Exception {
        return taskService.editTask(task_id, updateStatus);
    }

}
