package com.example.Task.Management.DTOs.RequestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskRequest {

    String userId;

    String title;

    String description;

    LocalDate dueDate;

    String status;

}
