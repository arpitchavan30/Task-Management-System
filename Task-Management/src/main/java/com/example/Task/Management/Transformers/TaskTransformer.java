package com.example.Task.Management.Transformers;

import com.example.Task.Management.DTOs.RequestDTO.TaskRequest;
import com.example.Task.Management.DTOs.ResponseDTO.TaskResponse;
import com.example.Task.Management.Enums.Status;
import com.example.Task.Management.Model.Task;

public class TaskTransformer {

    public static Task TaskRequestToTask(TaskRequest taskRequest){
        return Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                .status(Status.valueOf(taskRequest.getStatus().toUpperCase()))
                .build();
    }

    public static TaskResponse TaskToTaskResponse(Task task){
        return TaskResponse.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .build();
    }
}
