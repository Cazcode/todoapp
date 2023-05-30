package com.cazcode.todoapp.controller;

import com.cazcode.todoapp.persistence.entity.Task;
import com.cazcode.todoapp.persistence.entity.TaskStatus;
import com.cazcode.todoapp.service.TaskService;
import com.cazcode.todoapp.service.dto.TaskInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDto dto){
        return this.service.createTask(dto);
    }

    @GetMapping
    public List<Task> findAll(){
        return service.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByTaskStatus(@PathVariable("status")TaskStatus status){
        return service.findAllByTaskStatus(status);
    }

    @PatchMapping("/finish/{id}")
    public ResponseEntity<Void> maskAsFinished(@PathVariable("id") Long id){
        this.service.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaskbyId(@PathVariable("id") Long id){
        this.service.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
