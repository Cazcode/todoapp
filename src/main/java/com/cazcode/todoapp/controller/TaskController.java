package com.cazcode.todoapp.controller;

import com.cazcode.todoapp.persistence.entity.Task;
import com.cazcode.todoapp.persistence.entity.TaskStatus;
import com.cazcode.todoapp.service.TaskService;
import com.cazcode.todoapp.service.dto.TaskInDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully created task"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Authorized denied"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "502", description = "Error with upstream services"),
    })
    @PostMapping
    public Task createTask(@RequestBody TaskInDto dto){
        return this.service.createTask(dto);
    }

    @Operation(summary = "Get All Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully get all task"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Authorized denied"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "502", description = "Error with upstream services"),
    })
    @GetMapping
    public List<Task> findAll(){
        return service.findAll();
    }

    @Operation(summary = "Get All Task by Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully get all task by status"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Authorized denied"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "502", description = "Error with upstream services"),
    })
    @GetMapping("/status/{status}")
    public List<Task> findAllByTaskStatus(@PathVariable("status")TaskStatus status){
        return service.findAllByTaskStatus(status);
    }

    @Operation(summary = "Finish Task by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully Finish Task"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Authorized denied"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "502", description = "Error with upstream services"),
    })
    @PatchMapping("/finish/{id}")
    public ResponseEntity<Void> maskAsFinished(@PathVariable("id") Long id){
        this.service.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Task by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfully Delete Task"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Authorized denied"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "502", description = "Error with upstream services"),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaskbyId(@PathVariable("id") Long id){
        this.service.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
