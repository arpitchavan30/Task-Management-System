package com.example.Task.Management.DTOs.ResponseDTO;

import com.example.Task.Management.Enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskResponse {

    String taskId;

    String title;

    String description;

    LocalDate dueDate;

    Status status;
}
