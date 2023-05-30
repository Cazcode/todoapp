package com.cazcode.todoapp.service;

import com.cazcode.todoapp.exception.ToDoExceptions;
import com.cazcode.todoapp.mapper.TaskInDtotoTask;
import com.cazcode.todoapp.persistence.entity.Task;
import com.cazcode.todoapp.persistence.entity.TaskStatus;
import com.cazcode.todoapp.persistence.repository.TaskRepository;
import com.cazcode.todoapp.service.dto.TaskInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    private final TaskInDtotoTask mapper;

    public TaskService(TaskRepository repository, TaskInDtotoTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDto taskInDto){
        Task task = mapper.map(taskInDto);
        return this.repository.save(task);
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        repository.markTaskAsFinished(id);
    }


    public void deleteTaskById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
