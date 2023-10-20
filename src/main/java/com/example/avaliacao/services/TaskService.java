package com.example.avaliacao.services;

import com.example.avaliacao.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private List<TaskEntity> tasks;

    public TaskService(){
        tasks = new ArrayList<>();
    }

    //list all
    public List<TaskEntity> searchTasks() throws Exception{
        if (tasks.isEmpty()){
            throw new Exception("Task is empty!");
        } else {
            return tasks;
        }
    }

    //add
    public TaskEntity newTask(TaskEntity task){
        tasks.add(task);
        return task;
    }

    //list id
    public TaskEntity searchTask(Long task_id) throws  Exception{
        Optional<TaskEntity> task = tasks.stream().filter(t -> t.getTask_id() == task_id).findFirst();

        if (task.isPresent()){
            return task.get();
        } else {
            throw new Exception("Task not found!");
        }
    }

    //edit
    public TaskEntity editTask(Long task_id, TaskEntity task) throws Exception{
        Optional<TaskEntity> editedTask = tasks.stream().filter(t -> t.getTask_id() == task_id).findFirst();

        if (editedTask.isPresent()){
            editedTask.get().setTitle(task.getTitle());
            editedTask.get().setDescription(task.getDescription());
            editedTask.get().setStatus(task.getStatus());
            return task;
        } else {
            throw new Exception("Task not found");
        }
    }

    public TaskEntity editStatus(Long task_id, TaskEntity task) throws Exception{
        Optional<TaskEntity> editedTask = tasks.stream().filter(t -> t.getTask_id() == task_id).findFirst();

        if (editedTask.isPresent()){
            editedTask.get().setStatus(task.getStatus());
            return task;
        } else {
            throw new Exception("Task not found");
        }
    }

    public void deleteTask(Long task_id){
        tasks.removeIf(task -> task.getTask_id() == task_id);

    }


}
