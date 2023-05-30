package com.cazcode.todoapp.mapper;

import com.cazcode.todoapp.persistence.entity.Task;
import com.cazcode.todoapp.persistence.entity.TaskStatus;
import com.cazcode.todoapp.service.dto.TaskInDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDtotoTask implements IMapper<TaskInDto, Task> {

    @Override
    public Task map(TaskInDto in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
